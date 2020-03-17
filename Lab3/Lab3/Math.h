#include<math.h>

#pragma once
class Math
{
private:
	//q = 0.05
	double koharenaTable[18][12] = {
		{0,	  1,	2,   3,     4,   5,    6,    7,    8,    9,    10,    16},
		{2, 9985, 9750, 9392, 9057, 8772, 8534, 8332, 8159, 8010, 7880, 7341},
		{3, 9669, 8709, 7977, 7457, 7071, 6771, 6530, 6333, 6167, 6025, 5466},
		{4, 9065, 7679, 6841, 6287, 5892, 5598, 5365, 5175, 5017, 4884, 4366},
		{5, 8412, 6838, 5981, 5440, 5063, 4783, 4564, 4387, 4241, 4118, 3645},
		{6, 7808, 6161, 5321, 4803, 4447, 4184, 3980, 3817, 3682, 3568, 3135},
		{7, 7271, 5612, 4800, 4307, 3974, 3726, 3535, 3384, 3259, 3154, 2756},
		{8, 6798, 5157, 4377, 3910, 3595, 3362, 3185, 3043, 2926, 2829, 2462},
		{9, 6385, 4775, 4027, 3584, 3268, 3067, 2901, 2768, 2659, 2568, 2226},
		{10, 6020, 4450, 3733, 3311, 3029, 2823, 2666, 2541, 5439, 2353, 2032},
		{12, 5410, 3924, 3264, 2880, 2624, 2439, 2299, 2187, 2098, 2020, 1737},
		{15, 4709, 3346, 2758, 2419, 2159, 2034, 1911, 1815, 1736, 1671, 1429},
		{20, 3894, 2705, 2205, 1921, 1735, 1602, 1501, 1422, 1357, 1303, 1108},
		{24, 3434, 2354, 1907, 1656, 1493, 1374, 1286, 1216, 1160, 1113,  942},
		{30, 2929, 1980, 1593, 1377, 1377, 1137, 1061, 1002,  958,  921,  771},
		{40, 2370, 1576, 1259, 1082,  968,  887,  827,  780,  745,  713,  595},
		{60, 1737, 1131,  895,  766,  682,  623,  583,  552,  520,  497,  411},
		{120, 998,  632,  495,  419,  371,  337,  312,  292,  279,  266,  218} };

	double studentaTable[30] = {
		12.71, 4.303, 3.182, 2.776, 2.571, 2.447, 2.365, 2.306, 2.262, 2.228, 2.201, 2.179, 2.160, 2.145, 2.131, 2.120, 2.110, 2.101, 2.093, 
		2.086, 2.080, 2.074, 2.069, 2.064, 2.060, 2.056, 2.052, 2.048, 2.045, 2.042
	};

	double fisherTable[18][9] = {
		{0,	  1,	 2,     3,     4,     5,    6,    12,    24},
		{1, 164.4, 199.5, 215.7, 224.6, 230.2, 234, 244.9, 249},
		{2, 18.5, 19.2, 19.2, 19.3, 19.3, 19.3, 19.4, 19.4},
		{3, 10.1, 9.6, 9.3, 9.1, 9, 8.9, 8.7, 8.6},
		{4, 7.7, 6.9, 6.6, 6.4, 6.3, 6.2, 5.9, 5.8},
		{5, 6.6, 5.8, 5.4, 5.2, 5.1, 5, 4.7, 4.5},
		{6, 6.0, 5.1, 4.8, 4.5, 4.4, 4.3, 4, 3.8},
		{7, 5.5, 4.7, 4.4, 4.1, 4.0, 3.9, 3.6, 3.4},
		{8, 5.3, 4.5, 4.1, 3.8, 3.7, 3.6, 3.3, 3.1},
		{9, 5.1, 4.3, 3.9, 3.6, 3.5, 3.4, 3.1, 2.9},
		{10, 5.0, 4.1, 3.7, 3.5, 3.3, 3.2, 2.9, 2.7},
		{11, 4.8, 4.0, 3.6, 3.4, 3.2, 3.1, 2.8, 2.6},
		{12, 4.8, 3.9, 3.5, 3.3, 3.1, 3.0, 2.7, 2.5},
		{13, 4.7, 3.8, 3.4, 3.2, 3.0, 2.9, 2.6, 2.4},
		{14, 4.6, 3.7, 3.3, 3.1, 3.0, 2.9, 2.5, 2.3},
		{15, 4.5, 3.7, 3.3, 3.1,  2.9,  2.8,  2.5,  2.3},
		{16, 4.5, 3.6,  3.2,  3.0,  2.9,  2.7,  2.4,  2.2},
		{17, 4.5,  3.6,  3.2,  3.0,  2.8,  2.7,  2.4,  2.2} };


public:
	double calcDispertion(double* arr, double midValue, int len) {
		double result = 0;

		for (int i = 0; i < len; i++) {
			result += (arr[i] - midValue) * (arr[i] - midValue) / len;
		}
		return result;
	}

	double koharenaMeasure(int f1, int f2) {
		for (int i = 0; i < 11; i++) {
			if (f1 >= koharenaTable[0][i] && f1 < koharenaTable[0][i + 1]) {
				for (int j = 0; j < 17; j++) {
					if (f2 >= koharenaTable[j][0] && f2 < koharenaTable[j + 1][0]) {
						return koharenaTable[i][j] / 10000.0;
					}
				}
			}
		}
	}

	double findCoeficentStudentsa(int* x, double* y, int len) {
		double result = 0;
		for (int i = 0; i < len; i++) {
			result += x[i] * y[i] / len;
		}

		return result;
	}

	double studentaMeasure(int f3) {
		return studentaTable[f3 - 1];
	}

	double findCoeficentFishera(double* x, double* y, int len) {
		double result = 0;
		for (int i = 0; i < len; i++) {
			result += (x[i] - y[i]) * (x[i] - y[i]);
		}

		return result;
	}

	double fisheraMeasure(int f3, int f4) {
		return fisherTable[f3][f4];
	}
};


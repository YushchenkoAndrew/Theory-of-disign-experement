#pragma once
#include<stdio.h>
#include<math.h>
#include<stdlib.h>

class Experiment
{
private:
	int x[3][2] = { {-1, -1}, {-1, 1}, {1, -1}};
	int** y;
	double avarageY[3];								//This array will constain average value of y(i, j)
	double a[5];
	double b[3];


	int m = 5;
	double p = 0.9;

	double dispersion[3];
	double Ruv[3];


	double table[5][8] = { 
		{0.00, 2.00, 6.00, 8.00, 10.00, 12,  15,   20},
		{0.99, 1.73, 2.16, 2.43, 2.62, 2.75, 2.9,  3.08},
		{0.98, 1.72, 2.13, 2.37, 2.54, 2.66, 2.8,  2.96},
		{0.95, 1.71, 2.10, 2.27, 2.41, 2.52, 2.64, 2.78},
		{0.90, 1.69, 2.00, 2.17, 2.29, 2.39, 2.49, 2.62} };

public:
	Experiment() {

	}

	Experiment(int m, double p) {
		this->m = m;
		this->p = p;
	
	}

	void calcAndPrint() {
		fillMatrix();
		calcDispersion();
		checkResult();

		//This code below end up all recurtion that we have
		if (recurtionIndex != 0) {
			recurtionIndex--;
			return;
		}
		calcFactor();
		makeNaturalization();
	}

private:
	void drawLine() {
		for (int i = 0; i < 10 + m * 2; i++)
			printf("----");

		printf("\n");
	}

	void fillMatrix() {
		y = new int*[3];

		drawLine();
		printf("| X1\t X2|\t\t\t|");
		for (int i = 0; i < m; i++)
			printf(" Y%d\t", i + 1);
		printf("Yavr\n");

		drawLine();
		for (int i = 0; i < 3; i++) {
			y[i] = new int[m];
			avarageY[i] = 0;
			printf("|%3d\t%3d|\t\t\t|", x[i][0], x[i][1]);
			for (int j = 0; j < m; j++) {
				y[i][j] = rand() % 101 - 60;
				printf("%3d\t", y[i][j]);
				avarageY[i] += double(y[i][j]) / m;
			}
			printf("%5.3f\n", avarageY[i]);
			a[i] = 0;
		}

		drawLine();
	}

	void calcDispersion() {
		double basicDeviation = sqrt(2 * (2 * m - 2) / (m * (m - 4)));

		for (int i = 0; i < 3; i++) {
			dispersion[i] = 0;
			for (int j = 0; j < m; j++) {
				dispersion[i] += (y[i][j] - avarageY[i]) * (y[i][j] - avarageY[i]) / m;
			}
		}

		int i = 0;
		for (int j = 1; j < 3; j++) {
			Ruv[j] = (1 - 2 / double(m)) * (dispersion[i] >= dispersion[j] ? dispersion[i] / dispersion[j] : dispersion[j] / dispersion[i]) - 1;
			Ruv[j] *= (Ruv[j] >= 0 ? 1 : (-1)) / basicDeviation;
			printf("\nRuv[%d] = %5.3f", j, Ruv[j]);
		}

		i = 1;
		Ruv[0] = (1 - 2 / double(m)) * (dispersion[i] >= dispersion[2] ? dispersion[i] / dispersion[2] : dispersion[2] / dispersion[i]) - 1;
		Ruv[0] *= (Ruv[0] >= 0 ? 1 : (-1)) / basicDeviation;
		printf("\nRuv[%d] = %5.3f\n", 0, Ruv[0]);
	}

	int recurtionIndex = 0;

	void checkResult() {
		double properResult;

		//Here I use biniry search and this value contain n / 2
		int right = 8;
		int left = 0;

		int* index = binirySearchCol(0, 8);

		int colIndex = (this->m - table[0][index[0]]) < (table[0][index[1]] - this->m) ? index[0] : index[1];						//This is column where exist proper Result for us

		int rowIndex;
		for (int i = 2; i < 5; i++) {
			if (p == table[i][0]) {
				rowIndex = i;
				break;
			}
			if ((p <= table[i - 1][0]) && (p > table[i][0])) {
				rowIndex = (table[i - 1][0] - p) < (p - table[i][0]) ? i - 1 : i;
				break;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (Ruv[i] > table[rowIndex][colIndex]) {
				m++;
				recurtionIndex++;
				printf("Dispertion is not Smooth : (\n\n\n");
				calcAndPrint();
				return;
			}

		}

		printf("Dispertion is Smooth : )\n\n");
	}


	int* binirySearchCol(int left, int right) {
		if ((left == right) || (left + 1 == right)) {
			int point[2] = { left, right };
			return point;
		}

		int temp = int((right + left) / 2);
		if (m >= table[0][temp]) {
			left = temp;
		}
		else {
			right = temp;
		}
		binirySearchCol(left, right);
	}

	void calcFactor() {
		double mx1 = 0;
		double mx2 = 0;
		double my = 0;

		for (int i = 0; i < 3; i++) {
			mx1 += double(x[i][0]) / 3;
			mx2 += double(x[i][1]) / 3;
			my += avarageY[i] / 3;
			a[0] += double(x[i][0]) * x[i][0] / 3;
			a[1] += double(x[i][0]) * x[i][1] / 3;
			a[2] += double(x[i][1]) * x[i][1] / 3;

			a[3] += double(x[i][0] * avarageY[i]) / 3;
			a[4] += double(x[i][1] * avarageY[i]) / 3;
		}

		double det = a[0] * a[2] + mx1 * a[1] * mx2 + mx2 * mx1 * a[1] - mx2 * a[0] * mx2 - a[1] * a[1] - a[2] * mx1 * mx1;

		b[0] = a[0] * a[2] * my + a[3] * a[1] * mx2 + a[4] * mx1 * a[1] - mx2 * a[0] * a[4] - a[1] * a[1] * my - a[2] * mx1 * a[3];
		b[1] = a[3] * a[2] + mx1 * a[4] * mx2 + mx2 * my * a[1] - mx2 * a[3] * mx2 - a[1] * a[4] - a[2] * my * mx1;
		b[2] = a[0] * a[4] + mx1 * a[1] * my + mx2 * mx1 * a[3] - my * a[0] * mx2 - a[3] * a[1] - a[4] * mx1 * mx1;

		for (int i = 0; i < 3; i++) {
			b[i] /= det;
		}
		drawLine();
		printf("N5:\n");

		for (int i = 0; i < 3; i++) {
			printf("Yavr = %5.3f\t---\tY = %5.3f\n", avarageY[i], (b[0] + b[1] * x[i][0] + b[2] * x[i][1]));
		}

	}

	void makeNaturalization() {
		double deltaX1 = double(abs(60 - 10)) / 2;
		double deltaX2 = double(abs(10 + 25)) / 2;
		double X10 = double(60 + 10) / 2;
		double X20 = double(10 - 25) / 2;

		a[0] = b[0] - b[1] * X10 / deltaX1 - b[2] * X20 / deltaX2;
		a[1] = b[1] / deltaX1;
		a[2] = b[2] / deltaX2;

		drawLine();
		printf("N6:\n");

		printf("Yavr = %5.3f\t---\tY = %5.3f\n", avarageY[0], (a[0] + a[1] * (10) + a[2] * (-25)));
		printf("Yavr = %5.3f\t---\tY = %5.3f\n", avarageY[1], (a[0] + a[1] * (10) + a[2] * (10)));
		printf("Yavr = %5.3f\t---\tY = %5.3f\n", avarageY[2], (a[0] + a[1] * (60) + a[2] * (-25)));
	}


};


#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include"Matrix.h"
#include"Math.h"


#pragma once
class Experement
{

	private:
		Matrix m;
		Math math;

		int x[4][4] = { {1, -1, -1, -1},
						{1, -1, 1, 1},
						{1, 1, -1, 1},
						{1, 1, 1, -1} };

		int Xplan[4][3] = { {10, -25, 10},
							{10, 10, 15},
							{60, -25, 15},
							{60, 10, 10} };

		int f1 = 2;
		int f2 = 4;

		double b[4];

		int mExp = 3;

		double Y[4][13];

		double Ymin = 192.34;
		double Ymax = 228.33;


	private:
		void drawLine() {
			printf("\n");
			for (int i = 0; i < 8; i++) {
				printf("-------");
			}
			printf("\n");
		}

	public:
		void calcAndPrintResult() {
			double mx[3] = { 0, 0, 0 };
			double my[4];
			double mYtotal = 0;
			double a[9];
			drawLine();
			printf("|  X1\t  X2\t  X3\t|\tY1\t Y2\t   Y3\t|");
			drawLine();
			for (int i = 0; i < 4; i++) {
				my[i] = 0;
				printf("|%4d\t%4d\t%4d\t|", Xplan[i][0], Xplan[i][1], Xplan[i][2]);
				for (int j = 0; j < mExp; j++) {
					Y[i][j] = (rand() % int(Ymax - Ymin)) + Ymin;

					printf("%10.3f", Y[i][j]);

					my[i] += double(Y[i][j]) / mExp;
				}

				for (int j = 0; j < 3; j++) {
					mx[j] += double(Xplan[i][j]) / 4;
				}
				printf("\t|");
				drawLine();
			}

			mYtotal = (my[0] + my[1] + my[2] + my[3]) / 4;


			//a1, a2, a3
			a[0] = double(Xplan[0][0] * my[0] + Xplan[1][0] * my[1] + Xplan[2][0] * my[2] + Xplan[3][0] * my[3]) / 4;
			a[1] = double(Xplan[0][1] * my[0] + Xplan[1][1] * my[1] + Xplan[2][1] * my[2] + Xplan[3][1] * my[3]) / 4;
			a[2] = double(Xplan[0][2] * my[0] + Xplan[1][2] * my[1] + Xplan[2][2] * my[2] + Xplan[3][2] * my[3]) / 4;

			//a11, a22, a33
			a[3] = double(Xplan[0][0] * Xplan[0][0] + Xplan[1][0] * Xplan[1][0] + Xplan[2][0] * Xplan[2][0] + Xplan[3][0] * Xplan[3][0]) / 4;
			a[4] = double(Xplan[0][1] * Xplan[0][1] + Xplan[1][1] * Xplan[1][1] + Xplan[2][1] * Xplan[2][1] + Xplan[3][1] * Xplan[3][1]) / 4;
			a[5] = double(Xplan[0][2] * Xplan[0][2] + Xplan[1][2] * Xplan[1][2] + Xplan[2][2] * Xplan[2][2] + Xplan[3][2] * Xplan[3][2]) / 4;

			//a12, a13, a23
			a[6] = double(Xplan[0][0] * Xplan[0][1] + Xplan[1][0] * Xplan[1][1] + Xplan[2][0] * Xplan[2][1] + Xplan[3][0] * Xplan[3][1]) / 4;
			a[7] = double(Xplan[0][0] * Xplan[0][2] + Xplan[1][0] * Xplan[1][2] + Xplan[2][0] * Xplan[2][2] + Xplan[3][0] * Xplan[3][2]) / 4;
			a[8] = double(Xplan[0][2] * Xplan[0][1] + Xplan[1][2] * Xplan[1][1] + Xplan[2][2] * Xplan[2][1] + Xplan[3][2] * Xplan[3][1]) / 4;

			double matrix[4][4] = {
				{1,     mx[0], mx[1], mx[2]},
				{mx[0],  a[3],  a[6],  a[7]},
				{mx[1],  a[6],  a[4],  a[8]},
				{mx[2],	 a[7],	a[8],  a[5]} };


			double det = m.findDeterminant(matrix, 4);

			double matrix1[4][4] = {
				{mYtotal,     mx[0], mx[1], mx[2]},
				{a[0],  a[3],  a[6],  a[7]},
				{a[1],  a[6],  a[4],  a[8]},
				{a[2],	 a[7],	a[8],  a[5]} };

			b[0] = m.findDeterminant(matrix1, 4) / det;

			double matrix2[4][4] = {
				{1,   mYtotal, mx[1], mx[2]},
				{mx[0],  a[0],  a[6],  a[7]},
				{mx[1],  a[1],  a[4],  a[8]},
				{mx[2],	 a[2],	a[8],  a[5]} };

			b[1] = m.findDeterminant(matrix2, 4) / det;

			double matrix3[4][4] = {
				{1,     mx[0],mYtotal, mx[2]},
				{mx[0],  a[3],  a[0],  a[7]},
				{mx[1],  a[6],  a[1],  a[8]},
				{mx[2],	 a[7],	a[2],  a[5]} };

			b[2] = m.findDeterminant(matrix3, 4) / det;

			double matrix4[4][4] = {
				{1,     mx[0], mx[1], mYtotal},
				{mx[0],  a[3],  a[6],  a[0]},
				{mx[1],  a[6],  a[4],  a[1]},
				{mx[2],	 a[7],	a[8],  a[2]} };

			b[3] = m.findDeterminant(matrix4, 4) / det;

			printf("\nEquation:\nY = ");
			for (int i = 0; i < 4; i++) {
				printf("%5.3f ", b[i]);
				if (i != 0) {
					printf("* X%d ", i);
				}
				if (i != 3) {
					printf("+ ");
				}
			}

			printf("\n");

			double dispertion[4] = { math.calcDispertion(Y[0], my[0], mExp),
			math.calcDispertion(Y[1], my[1], mExp),
			math.calcDispertion(Y[2], my[2], mExp),
			math.calcDispertion(Y[3], my[3], mExp) };

			double totalDispartion = 0;
			double maxDisapertion = INT_MIN;

			for (int i = 0; i < 4; i++) {
				if (dispertion[i] > maxDisapertion) {
					maxDisapertion = dispertion[i];
				}
				totalDispartion += dispertion[i];
			}
			printf("Koharena Measure:\n");
			printf("Gp = %5.3f\n", maxDisapertion / totalDispartion);
			printf("Gt = %5.3f\n\n", math.koharenaMeasure(f1, f2));

			if (maxDisapertion / totalDispartion > math.koharenaMeasure(f1, f2)) {
				mExp++;
				printf("Dispartion is not Smooth");
				calcAndPrintResult();
			}

			double beta[4] = { math.findCoeficentStudentsa(new int[4]{1, 1, 1, 1}, my, 4),
			math.findCoeficentStudentsa(new int[4]{-1, -1, 1, 1}, my, 4),
			math.findCoeficentStudentsa(new int[4]{-1, 1, -1, 1}, my, 4),
			math.findCoeficentStudentsa(new int[4]{-1, 1, 1, -1}, my, 4) };

			double t[4];

			int len = 0;
			double yPract[4] = {0, 0, 0, 0};

			printf("Studentsa Measure:\n");
			for (int i = 0; i < 4; i++) {
				t[i] = beta[i] / sqrt(totalDispartion / (4 * 4 * mExp)) * (beta[i] < 0 ? (-1) : 1);
				
				printf("t%d = %8.3f\n", i, t[i]); 
				for (int j = 0; j < 4; j++) {
					yPract[j] += b[i] * (t[i] < math.studentaMeasure(f1 * f2) ? 0 : 1) * (i == 0 ? 1 : Xplan[j][i - 1]);

				}

			}

			printf("t = %8.3f\n\n", math.studentaMeasure(f1 * f2));

			printf("Fishera Measure:\n");


			printf("Sad = %8.3f\n", math.findCoeficentFishera(yPract, my, 4) * mExp / 2);

			printf("Fp = %8.3f\n", (math.findCoeficentFishera(yPract, my, 4) * mExp / 2) / ( totalDispartion / (4 * 4 * mExp)));
			printf("Ft = %8.3f\n", math.fisheraMeasure(8, 2));
			printf((math.fisheraMeasure(8, 2) < (math.findCoeficentFishera(yPract, my, 4) * mExp / 2) / (totalDispartion / (4 * 4 * mExp)) ? "Equation is not edequate" :
				"Equation is edequate"));

			printf("\n");

		}



	};



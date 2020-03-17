#pragma once
#include<stdio.h>
#include<stdlib.h>

class Experiment
{
private:
	double Y[8];
	double Ys;
	double X[8][3];
	double Xn[8][3];
	double X0[3];
	double dx[3];
	double result = INT_MIN;
	int index;


public:
	void calculation(double a[4], int edge) {
		for (int j = 0; j < 3; j++) {
			double min = INT_MAX;
			double max = INT_MIN;
			for (int i = 0; i < 8; i++) {
				X[i][j] = int(rand() % edge);

				Y[i] = j == 0 ? a[0] + a[1] * X[i][j] : Y[i] + a[j + 1] * X[i][j];

				min = min < X[i][j] ? min : X[i][j];
				max = max > X[i][j] ? max : X[i][j];
			}

			X0[j] = (min + max) / 2;
			dx[j] = X0[j] - min;

			Ys = j == 0 ? a[0] + a[1] * X0[j] : Ys + a[j + 1] * X0[j];
			Xn[0][j] = (-1) * X0[j] / dx[j];
		}
		findMissingParameters();
	}

private:
	void findMissingParameters() {
		result = (Y[0] - Ys) * (Y[0] - Ys);
		index = 0;

		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 3; j++) {
				Xn[i][j] = X[i][j] / dx[j] + Xn[0][j];
			}
			double value = (Y[i] - Ys) * (Y[i] - Ys);
			if (result < value) {
				result = value;
				index = i;
			}
		}
		for (int j = 0; j < 3; j++) {
			Xn[0][j] += X[0][j] / dx[j];
		}
	}

private:
	void printLine() {

		for (int i = 0; i < 32; i++)
			printf("---");
		printf("\n");
	}

public:
	void printResult() {
		printLine();
		printf("|N|\tX0\tX1\tX2\t|\tY\t|\t\t|\tXn1\tXn2\tXn3\t|\n");
		printLine();


		for (int i = 0; i < 8; i++) {
			printf("|%d|%8.3f %8.3f %8.3f\t|%10.3f\t|\t\t|%10.3f %8.3f %8.3f\t|\n", i + 1, X[i][0], X[i][1], X[i][2], Y[i], Xn[i][0], Xn[i][1], Xn[i][2]);
		}

		printLine();

		printf("|x0|%8.3f %8.3f %8.3f\t|\t\t|\t\t|\t \t \t\t|\n", X0[0], X0[1], X0[2]);
		printf("|dx|%8.3f %8.3f %8.3f\t|\t\t|\t\t|\t \t \t\t|\n", dx[0], dx[1], dx[2]);

		printLine();

		printf("Ys = %f\nmax((Y - Ys)^2) = %f\t(%5.3f, %5.3f, %5.3f)\n", Ys, result, X[index][0], X[index][1], X[index][2]);
	}
};


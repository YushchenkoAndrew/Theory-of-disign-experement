#pragma once
class Matrix
{
public:
	double findDeterminant3(double** matrix) {
		return matrix[0][0] * matrix[1][1] * matrix[2][2] + matrix[1][0] * matrix[2][1] * matrix[0][2]
			+ matrix[2][0] * matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1] * matrix[2][0]
			- matrix[1][2] * matrix[2][1] * matrix[0][0] - matrix[2][2] * matrix[0][1] * matrix[1][0];
	}

	const static int setDimention = 4;

	double findDeterminant(double matrix[setDimention][setDimention], int len) {
		double result = 0;

		for (int i = 0; i < len; i++) {
			double** newMatrix = cutRowMatrix(clone(matrix, setDimention), i, len);

			result += findDeterminant3(newMatrix) * matrix[i][0] * (i % 2 == 1 ? -1 : 1);
		}
		return result;
	}

	double** cutRowMatrix(double* matrix[], int index, int len) {
		double** newMatrix = new double* [len - 1];

		int correction = 0;

		for (int i = 0; i < len; i++) {
			if (i == index) {
				correction++;
				continue;
			}

			newMatrix[i - correction] = new double[len - 1];
			for (int j = 0; j < len - 1; j++) {
				newMatrix[i - correction][j] = matrix[i][j + 1];
			}
		}
		return newMatrix;
	}

	double** clone(double matrix[][setDimention], int len) {
		double** newMatrix = new double* [len];

		for (int i = 0; i < len; i++) {
			newMatrix[i] = new double[len];
			for (int j = 0; j < len; j++) {
				newMatrix[i][j] = matrix[i][j];
			}
		}
		return newMatrix;
	}
};


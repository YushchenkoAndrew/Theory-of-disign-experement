#include"Experiment.h"

using namespace std;

double a[4] = {1, 2, 3, 4};

int main() {
	Experiment exp;

	exp.calculation(a, 20);
	exp.printResult();

	return 0;
}
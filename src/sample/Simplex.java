package sample;

public class Simplex {

    private static final boolean MAXIMIZE = false;
    private static final boolean MINIMIZE = true;
    private double[][] tableaux; // таблица
    private int countOfConstraints; // кол-во ограничений
    private int countOfOriginalVariables; // кол-во исходных переменных
    private boolean maxOrMin;
    private int[] basis; // basis[i] = это базовая переменная, которая соответствует строке i
    public static double[] array; // публичный массив, созданный для передачи массива ответов в класс Controller
    public static double maxValue = 0; // костыль, переменнная, которая определяет максимальный элемент в массиве ответов
    public static double cf;

    public Simplex(double[][] tableaux, int numberOfConstraint,
                   int numberOfOriginalVariable, boolean maxOrMin) {
        this.maxOrMin = maxOrMin;
        this.countOfConstraints = numberOfConstraint;
        this.countOfOriginalVariables = numberOfOriginalVariable;
        this.tableaux = tableaux;

        basis = new int[countOfConstraints];
        for (int i = 0; i < countOfConstraints; i++)
            basis[i] = countOfOriginalVariables + i;
        solve();
    }
    // test
    public static void main() {

        double[] mainFunction = {-1, -1, -1, -1, -1, -1, -1};
        double[][] constraintLeftSide = {
                
                {0, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 0}

        };
        Constraint[] constraintOperator = {Constraint.greaterThan,
                Constraint.greaterThan, Constraint.greaterThan, Constraint.greaterThan, Constraint.greaterThan, Constraint.greaterThan, Constraint.greaterThan};
        double[] constraintRightSide = {Controller.nSun, Controller.nMon, Controller.nTue, Controller.nWe, Controller.nThur, Controller.nFri, Controller.nSat};
        for (int i = 0; i < constraintRightSide.length; i++) {
            if (constraintRightSide[i] > maxValue) {
                maxValue = constraintRightSide[i];
            }
        }
        System.out.println(maxValue);
        Modeler model = new Modeler(constraintLeftSide, constraintRightSide,
                constraintOperator, mainFunction);

        Simplex simplex = new Simplex(model.getTableaux(),
                model.getNumberOfConstraint(),
                model.getNumberOfOriginalVariable(), MINIMIZE);

        double[] x = simplex.primal();
        array = new double[7];
        for (int i = 0; i < x.length; i++) {
            System.out.println("x[" + i + "] = " + x[i]);
            array[i] = x[i];
            System.out.println(x[i] + " ");
        }

        System.out.println("Решение: " + simplex.value());

    }

    // Запуск симплекс-алгоритма с начала
    private void solve() {
        while (true) {

            show();
            int q = 0;

            if (maxOrMin) {

                q = positiveIndexFinder();
            } else {
                q = negativeIndexFinder();
            }

            if (q == -1) {
                break; // optimal
            }

            int p = minRatioRule(q);
            if (p == -1) {
                throw new ArithmeticException("Задача не ограничена");
            }
            // Преобразовани
            jgConversion(p, q);

            // Обновление базиса
            basis[p] = q;

        }
    }

    // найти строку p, используя правило минимума (-1, если такой строки нет)
    private int minRatioRule(int q) {
        int p = -1;
        for (int i = 0; i < countOfConstraints; i++) {
            if (tableaux[i][q] <= 0) {
                continue;
            }
            else if (p == -1) {
                p = i;
            }
            else if ((tableaux[i][countOfConstraints
                    + countOfOriginalVariables] / tableaux[i][q]) < (tableaux[p][countOfConstraints
                    + countOfOriginalVariables] / tableaux[p][q]))
                p = i;
        }
        return p;
    }

    // Gauss-Jordan преобразование
    private void jgConversion(int p, int q) {

        for (int i = 0; i <= countOfConstraints; i++)
            for (int j = 0; j <= countOfConstraints
                    + countOfOriginalVariables; j++)
                if (i != p && j != q)
                    tableaux[i][j] -= tableaux[p][j] * tableaux[i][q]
                            / tableaux[p][q];

        for (int i = 0; i <= countOfConstraints; i++)
            if (i != p)
                tableaux[i][q] = 0.0;

        for (int j = 0; j <= countOfConstraints + countOfOriginalVariables; j++)
            if (j != q)
                tableaux[p][j] /= tableaux[p][q];
        tableaux[p][q] = 1.0;
    }

    // Возвращает оптимальное значение цели
    public double value() {
        return -tableaux[countOfConstraints][countOfConstraints
                + countOfOriginalVariables];
    }

    public double[] primal() {
        double[] x = new double[countOfOriginalVariables];
        for (int i = 0; i < countOfConstraints; i++)
            if (basis[i] < countOfOriginalVariables)
                x[basis[i]] = tableaux[i][countOfConstraints
                        + countOfOriginalVariables];

        return x;
    }

    // Вывод в консоль
    public void show() {
        System.out.println("M = " + countOfConstraints);
        System.out.println("N = " + countOfOriginalVariables);
        for (int i = 0; i <= countOfConstraints; i++) {
            for (int j = 0; j <= countOfConstraints
                    + countOfOriginalVariables; j++) {
                System.out.printf("%7.2f ", tableaux[i][j]);
            }
            System.out.println();
        }
        System.out.println("value = " + value());
        cf = tableaux[7][14];
        System.out.println(cf);
        for (int i = 0; i < countOfConstraints; i++)
            if (basis[i] <= countOfOriginalVariables)
                System.out.println("x_"
                        + basis[i]
                        + " = "
                        + tableaux[i][countOfConstraints
                        + countOfOriginalVariables]);
        System.out.println();
    }

    private enum Constraint {
        lessThan, equal, greaterThan, greatherThanOrEqual
    }

    // индекс наибольшего положительного из небазисного столбща
    private int positiveIndexFinder() {
        int q = 0;
        int positiveCounter = 0;
        for (int j = 1; j < countOfConstraints + countOfOriginalVariables; j++) {
            if ((tableaux[countOfConstraints][j]) < (tableaux[countOfConstraints][q]))
                q = j;
        }
        for (int i = 0; i < countOfConstraints + countOfOriginalVariables; i++) {
            double result = (tableaux[countOfConstraints][i]);
            if (result > 0.1) {
                positiveCounter++;
            }
        }

        if (positiveCounter >= 1 && cf >= maxValue) {
            return -1;// вернём единицу
        } else
            return q;
    }

    // индекс наибольшего отрицательного из небазисного столбща
    private int negativeIndexFinder() {
        int q = 0;
        for (int j = 1; j < countOfConstraints + countOfOriginalVariables; j++)
            if (tableaux[countOfConstraints][j] < tableaux[countOfConstraints][q])
                q = j;

        if (tableaux[countOfConstraints][q] >= 0)
            return -1; // optimal
        else
            return q;
    }

    public static class Modeler {
        private double[][] a; // таблица
        private int countOfConstraints; // количество ограничений
        private int countOfOriginalVariables; // количество исходных переменных

        public Modeler(double[][] constraintLeftSide,
                       double[] constraintRightSide, Constraint[] constraintOperator,
                       double[] objectiveFunction) {
            countOfConstraints = constraintRightSide.length;
            countOfOriginalVariables = objectiveFunction.length;
            a = new double[countOfConstraints + 1][countOfOriginalVariables
                    + countOfConstraints + 1];

            // Инициализация таблицы
            for (int i = 0; i < countOfConstraints; i++) {
                for (int j = 0; j < countOfOriginalVariables; j++) {
                    a[i][j] = constraintLeftSide[i][j];
                }
            }

            for (int i = 0; i < countOfConstraints; i++)
                a[i][countOfConstraints + countOfOriginalVariables] = constraintRightSide[i];

            // это иницализация знаков
            for (int i = 0; i < countOfConstraints; i++) {
                int slack = 0;
                switch (constraintOperator[i]) {
                    case greaterThan:
                        slack = -1;
                        break;
                    case lessThan:
                        slack = 1;
                        break;
                    case greatherThanOrEqual:

                    default:
                }
                a[i][countOfOriginalVariables + i] = slack;
            }

            // целевая
            for (int j = 0; j < countOfOriginalVariables; j++)
                a[countOfConstraints][j] = objectiveFunction[j];


        }

        public double[][] getTableaux() {
            return a;
        }

        public int getNumberOfConstraint() {
            return countOfConstraints;
        }

        public int getNumberOfOriginalVariable() {
            return countOfOriginalVariables;
        }
    }


}
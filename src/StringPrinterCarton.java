final class StringPrinterCarton {
    public String printCarton(int[][] numeros) {
        final StringBuilder result = new StringBuilder();
        for (int[] numero : numeros) {
            for (int i : numero) {
                if (i == -1)
                    result.append("X  ");
                else if (i < 10)
                    result.append("0").append(i).append(" ");
                else
                    result.append(i).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private static void countingSort(int[] arr, int exp) {
        int[] salida = new int[arr.length];
        int[] conteo = new int[10];

        for (int num : arr)
            conteo[(num / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            conteo[i] += conteo[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            salida[conteo[digit] - 1] = arr[i];
            conteo[digit]--;
        }

        System.arraycopy(salida, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        try {

            BufferedReader br = new BufferedReader(new FileReader("numeros.txt"));
            String linea = br.readLine();
            br.close();

            String[] partes = linea.split("\\s+");
            int[] arr = new int[partes.length];

            for (int i = 0; i < partes.length; i++) {
                arr[i] = Integer.parseInt(partes[i]);
            }

            radixSort(arr);

            PrintWriter pw = new PrintWriter("resultado.txt");
            pw.println("Resultado de radix sort:");
            for (int n : arr) pw.print(n + " ");
            pw.close();

            System.out.println("RadixSort generado en resultado.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


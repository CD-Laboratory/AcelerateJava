import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program2 {

	public static void main(String[] args) {

		File file = new File("D:\\Users\\Dell\\Documents\\wrk_estudo\\AcelerateJava\\TratamentoDeErros\\arquivos\\in.txt");
		Scanner sc = null;

		try {
			sc = new Scanner(file);
			
			while (sc.hasNext()) {
				System.out.println(sc.nextLine());
			}
			
		} catch (IOException e) {
			System.out.println("Erro openning file: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}	
	}
}

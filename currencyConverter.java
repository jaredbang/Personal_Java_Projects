import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter amount: ");
            double amount = input.nextDouble();

            System.out.println("Enter source currency (3-letter code): ");
            String sourceCurrency = input.next().toUpperCase();

            System.out.println("Enter target currency (3-letter code): ");
            String targetCurrency = input.next().toUpperCase();

            String appId = "4636011c898d43fd9faab45f4483d4e6"; // Replace with your Open Exchange Rates API App ID
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=" + appId + "&base=" + sourceCurrency + "&symbols=" + targetCurrency);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            Scanner reader = new Scanner(connection.getInputStream());

            StringBuilder responseBuilder = new StringBuilder();
            while (reader.hasNextLine()) {
                responseBuilder.append(reader.nextLine());
            }

            String response = responseBuilder.toString();
            double rate = Double.parseDouble(response.split("\"" + targetCurrency + "\":")[1].split("}")[0]);

            double convertedAmount = amount * rate;

            System.out.println(amount + " " + sourceCurrency + " = " + convertedAmount + " " + targetCurrency);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

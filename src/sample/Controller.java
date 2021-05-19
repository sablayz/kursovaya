package sample;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class Controller {
    @FXML
    private TextField txtMon;

    @FXML
    private Button btAuthor;

    @FXML
    private TextField txtSat;

    @FXML
    private TextField txtSun;

    @FXML
    private TextField txtFri;

    @FXML
    private TextField txtThur;

    @FXML
    private TextField txtWe;

    @FXML
    private TextField txtTue;

    @FXML
    private Button btCalc;

    @FXML
    private Label laMon;

    @FXML
    private Label laFri;

    @FXML
    private Label laThur;

    @FXML
    private Label laSat;

    @FXML
    private Label laSun;

    @FXML
    private Label laMon1;

    @FXML
    private Label laTue;

    @FXML
    private Label laFri1;

    @FXML
    private Label laSat1;

    @FXML
    private Label laSun1;

    @FXML
    private Label laMon2;

    @FXML
    private Label laTue1;

    @FXML
    private Label laWe;

    @FXML
    private Label laSat2;

    @FXML
    private Label laSun2;

    @FXML
    private Label laMon3;

    @FXML
    private Label laTue2;

    @FXML
    private Label laThur1;

    @FXML
    private Label laWe1;

    @FXML
    private Label laSun3;

    @FXML
    private Label laMon4;

    @FXML
    private Label laTue3;

    @FXML
    private Label laFri2;

    @FXML
    private Label laThur2;

    @FXML
    private Label laWe2;

    @FXML
    private Label laTue4;

    @FXML
    private Label laFri3;

    @FXML
    private Label laThur3;

    @FXML
    private Label laWe3;

    @FXML
    private Label laSat3;

    @FXML
    private Label laFri4;

    @FXML
    private Label laThur4;

    @FXML
    private Label laWe4;

    @FXML
    private Label laSat4;

    @FXML
    private Label laSun4;

    @FXML
    private Label laSum;

    @FXML
    private Label laSum1;

    @FXML
    private Label laSum2;

    @FXML
    private Label laSum3;

    @FXML
    private Label laSum4;

    @FXML
    private Label laSum5;

    @FXML
    private Label laSum6;

    @FXML
    private Label laSum7;


    public static double nMon;
    public static double nTue;
    public static double nWe;
    public static double nThur;
    public static double nFri;
    public static double nSat;
    public static double nSun;

    @FXML
    void initialize() {


        // Запрет ввода символов, реальзован нерационально, но работает
        txtMon.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        txtTue.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        txtWe.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        txtThur.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        txtFri.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        txtSat.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        txtSun.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.getText().matches("\\d+")) {
                change.setText("");
            }
            return change;
        }));

        // Имплементация кнопки Автор
        btAuthor.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Об авторе");
            alert.setHeaderText("Курсовая работа ИО, Вариант 58. ");
            alert.setContentText("Выполнил Карпович В.А. А91ИСТ2, Минск 2021");
            alert.showAndWait();
                });

        // Имплементация кнопки "Рассчитать"
        btCalc.setOnAction(actionEvent -> {

            // Инициализация переменных, введённых в поле
            nMon = Double.parseDouble(txtMon.getText());
            nTue = Double.parseDouble(txtTue.getText());
            nWe = Double.parseDouble(txtWe.getText());
            nThur = Double.parseDouble(txtThur.getText());
            nFri = Double.parseDouble(txtFri.getText());
            nSat = Double.parseDouble(txtSat.getText());
            nSun = Double.parseDouble(txtSun.getText());

            // Вызов расчёта
            Simplex.main();

            // Инициалиазция результата
            int Monday = (int) Math.round(Simplex.array[0]);
            int Tuesday = (int) Math.round(Simplex.array[1]);
            int Wednesday = (int) Math.round(Simplex.array[2]);
            int Thursday = (int) Math.round(Simplex.array[3]);
            int Friday = (int) Math.round(Simplex.array[4]);
            int Saturday = (int) Math.round(Simplex.array[5]);
            int Sunday = (int) Math.round(Simplex.array[6]);

            // Вывод результата в форму
            laSum7.setText(String.valueOf(Monday + Thursday + Friday + Saturday + Sunday + Tuesday + Wednesday));
            laSum.setText(String.valueOf(Monday + Thursday + Friday + Saturday + Sunday));
            laSum1.setText(String.valueOf(Monday + Tuesday + Friday + Saturday + Sunday));
            laSum2.setText(String.valueOf(Monday + Tuesday + Saturday + Sunday + Wednesday));
            laSum3.setText(String.valueOf(Monday + Tuesday + Sunday + Thursday + Wednesday));
            laSum4.setText(String.valueOf(Monday + Tuesday + Friday + Thursday + Wednesday));
            laSum5.setText(String.valueOf(Tuesday + Friday + Saturday + Thursday + Wednesday));
            laSum6.setText(String.valueOf(Friday + Saturday + Sunday + Thursday + Wednesday));

            laMon.setText(String.valueOf(Monday));
            laTue.setText(String.valueOf(Tuesday));
            laWe.setText(String.valueOf(Wednesday));
            laThur.setText(String.valueOf(Thursday));
            laFri.setText(String.valueOf(Friday));
            laSat.setText(String.valueOf(Saturday));
            laSun.setText(String.valueOf(Sunday));

            laMon1.setText(String.valueOf(Monday));
            laTue1.setText(String.valueOf(Tuesday));
            laWe1.setText(String.valueOf(Wednesday));
            laThur1.setText(String.valueOf(Thursday));
            laFri1.setText(String.valueOf(Friday));
            laSat1.setText(String.valueOf(Saturday));
            laSun1.setText(String.valueOf(Sunday));

            laMon2.setText(String.valueOf(Monday));
            laTue2.setText(String.valueOf(Tuesday));
            laWe2.setText(String.valueOf(Wednesday));
            laThur2.setText(String.valueOf(Thursday));
            laFri2.setText(String.valueOf(Friday));
            laSat2.setText(String.valueOf(Saturday));
            laSun2.setText(String.valueOf(Sunday));

            laMon3.setText(String.valueOf(Monday));
            laTue3.setText(String.valueOf(Tuesday));
            laWe3.setText(String.valueOf(Wednesday));
            laThur3.setText(String.valueOf(Thursday));
            laFri3.setText(String.valueOf(Friday));
            laSat3.setText(String.valueOf(Saturday));
            laSun3.setText(String.valueOf(Sunday));

            laMon4.setText(String.valueOf(Monday));
            laTue4.setText(String.valueOf(Tuesday));
            laWe4.setText(String.valueOf(Wednesday));
            laThur4.setText(String.valueOf(Thursday));
            laFri4.setText(String.valueOf(Friday));
            laSat4.setText(String.valueOf(Saturday));
            laSun4.setText(String.valueOf(Sunday));

            System.out.println(nMon);
            System.out.println(nTue);
            System.out.println(nWe);
            System.out.println(nThur);
            System.out.println(nFri);
            System.out.println(nSat);
            System.out.println(nSun);

        });
    }
}


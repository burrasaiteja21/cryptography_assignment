import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaesarCipherTool extends JFrame implements ActionListener {
    private JTextField textInput, shiftInput;
    private JComboBox<String> choiceBox;
    private JTextArea resultArea;

    public CaesarCipherTool() {
        setTitle("Caesar Cipher Tool");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel textLabel = new JLabel("Text:");
        inputPanel.add(textLabel);

        textInput = new JTextField();
        inputPanel.add(textInput);

        JLabel shiftLabel = new JLabel("Shift Value:");
        inputPanel.add(shiftLabel);

        shiftInput = new JTextField();
        inputPanel.add(shiftInput);

        JLabel choiceLabel = new JLabel("Choose:");
        inputPanel.add(choiceLabel);

        String[] choices = {"Encrypt", "Decrypt"};
        choiceBox = new JComboBox<>(choices);
        choiceBox.addActionListener(this); // Register listener for choiceBox
        inputPanel.add(choiceBox);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textInput.getText();
        int shift = Integer.parseInt(shiftInput.getText());
        String choice = (String) choiceBox.getSelectedItem();
        String result;

        if (!text.isEmpty() && !shiftInput.getText().isEmpty()) {
            if (choice.equals("Encrypt")) {
                result = encrypt(text, shift);
            } else {
                result = decrypt(text, shift);
            }
            resultArea.setText("Result:\n" + result);
        }
    }

    private String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift) % 26 + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift); // Decrypting is shifting back by 26 - shift
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CaesarCipherTool().setVisible(true);
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordManagerGUI extends JFrame {

    // Constants for character sets used in password generation
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+={}[]|;:<>,.?/~";
    private static final Random random = new SecureRandom(); // Secure random number generator

    // UI Components
    private JLabel passwordStrengthLabel; // Label to display password strength
    private JTextField passwordField; // Text field for entering password to check strength
    private JTextArea generatedPasswordTextArea; // Text area to display generated passwords
    private JCheckBox includeLowerCheckBox; // Checkbox to include lowercase letters in generated passwords
    private JCheckBox includeUpperCheckBox; // Checkbox to include uppercase letters
    private JCheckBox includeNumbersCheckBox; // Checkbox to include numbers
    private JCheckBox includeSpecialCheckBox; // Checkbox to include special characters
    private JTextField passwordLengthField; // Text field for entering desired password length

    // Constructor for the PasswordManagerGUI class
    public PasswordManagerGUI() {
        setTitle("Password Manager"); // Set the title of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setSize(400, 500); // Set the size of the JFrame
        setLocationRelativeTo(null); // Center the JFrame on the screen

        // Create a JPanel to hold the UI components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible layout
        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for GridBagLayout
        gbc.insets = new Insets(10, 10, 10, 10); // Set margins around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components fill horizontal space

        // Title Label
        JLabel titleLabel = new JLabel("Password Manager");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        mainPanel.add(titleLabel, gbc); // Add label to the panel with constraints

        // Password Label and Text Field
        JLabel passwordLabel = new JLabel("Enter Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(passwordField, gbc);

        // Check Strength Button
        JButton checkStrengthButton = new JButton("Check Strength");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(checkStrengthButton, gbc);
        checkStrengthButton.addActionListener(new ActionListener() { // Add action listener to the button
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPasswordStrength(); // Call method to check password strength
            }
        });

        // Password Strength Label
        passwordStrengthLabel = new JLabel();
        passwordStrengthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(passwordStrengthLabel, gbc);

        // Password Length Label and Text Field
        JLabel lengthLabel = new JLabel("Password Length:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lengthLabel, gbc);

        passwordLengthField = new JTextField("8"); // Default password length is 8
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(passwordLengthField, gbc);

        // Checkboxes for character set selection
        includeLowerCheckBox = new JCheckBox("Include Lowercase");
        includeLowerCheckBox.setSelected(true); // включено по умолчанию
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        mainPanel.add(includeLowerCheckBox, gbc);

        includeUpperCheckBox = new JCheckBox("Include Uppercase");
        includeUpperCheckBox.setSelected(true);  // включено по умолчанию
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        mainPanel.add(includeUpperCheckBox, gbc);

        includeNumbersCheckBox = new JCheckBox("Include Numbers");
        includeNumbersCheckBox.setSelected(true);  // включено по умолчанию
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        mainPanel.add(includeNumbersCheckBox, gbc);

        includeSpecialCheckBox = new JCheckBox("Include Special Chars");
        includeSpecialCheckBox.setSelected(false); // отключено по умолчанию
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        mainPanel.add(includeSpecialCheckBox, gbc);

        // Generate Password Button
        JButton generateButton = new JButton("Generate Password");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(generateButton, gbc);
        generateButton.addActionListener(new ActionListener() { // Add action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomPassword(); // Call method to generate password
            }
        });

        // Text Area for Generated Password
        generatedPasswordTextArea = new JTextArea();
        generatedPasswordTextArea.setEditable(false); // Make it read-only
        generatedPasswordTextArea.setWrapStyleWord(true); // Wrap words
        generatedPasswordTextArea.setLineWrap(true);    // Wrap lines
        generatedPasswordTextArea.setPreferredSize(new Dimension(200, 100)); // Set preferred size
        JScrollPane scrollPane = new JScrollPane(generatedPasswordTextArea); // Add scroll pane
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH; // Fill both directions
        mainPanel.add(scrollPane, gbc); // Add scroll pane to the panel

        // Exit Button
        JButton exitButton = new JButton("Exit");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(exitButton, gbc);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        add(mainPanel); // Add the main panel to the JFrame
        setVisible(true); // Make the JFrame visible
    }

    // Method to check password strength
    private void checkPasswordStrength() {
        String password = passwordField.getText(); // Get password from text field
        int strength = calculatePasswordStrength(password); // Calculate strength
        passwordStrengthLabel.setText("Password Strength: " + getStrengthString(strength)); // Display result
    }

    // Method to calculate password strength
    private int calculatePasswordStrength(String password) {
        if (password == null || password.isEmpty()) {
            return 0;
        }

        int strength = 0;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if (hasLower) strength++;
        if (hasUpper) strength++;
        if (hasDigit) strength++;
        if (hasSpecial) strength++;
        if (password.length() >= 8) strength++;

        return strength;
    }

    // Method to get string representation of password strength
    private String getStrengthString(int strength) {
        switch (strength) {
            case 0:
                return "Very Weak";
            case 1:
                return "Weak";
            case 2:
                return "Moderate";
            case 3:
                return "Strong";
            case 4:
            case 5:
                return "Very Strong";
            default:
                return "Invalid";
        }
    }

    // Method to generate a random password
    private void generateRandomPassword() {
        int length;
        try {
            length = Integer.parseInt(passwordLengthField.getText()); // Get length from text field
            if (length <= 0) {
                generatedPasswordTextArea.setText("Password length must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            generatedPasswordTextArea.setText("Invalid password length. Please enter a number.");
            return;
        }

        boolean includeLower = includeLowerCheckBox.isSelected(); // Get checkbox states
        boolean includeUpper = includeUpperCheckBox.isSelected();
        boolean includeNumbers = includeNumbersCheckBox.isSelected();
        boolean includeSpecial = includeSpecialCheckBox.isSelected();

        String password = generatePassword(length, includeLower, includeUpper, includeNumbers, includeSpecial); // Generate password
        generatedPasswordTextArea.setText("Generated Password: " + password); // Display password
    }

    // Method to generate the password based on user preferences
    private static String generatePassword(int length, boolean includeLower, boolean includeUpper, boolean includeNumbers, boolean includeSpecial) {
        StringBuilder password = new StringBuilder();
        String charSet = "";

        if (includeLower) charSet += LOWER_CASE;
        if (includeUpper) charSet += UPPER_CASE;
        if (includeNumbers) charSet += NUMBERS;
        if (includeSpecial) charSet += SPECIAL_CHARACTERS;

        if (charSet.isEmpty()) {
            return "At least one character set must be selected.";
        }

        // Ensure at least one character from each selected set is included
        if (includeLower) {
            password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
            length--;
        }
        if (includeUpper && length > 0) {
            password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
            length--;
        }
        if (includeNumbers && length > 0) {
            password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            length--;
        }
        if (includeSpecial && length > 0) {
            password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
            length--;
        }

        // Fill the remaining length with random characters from the combined set
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSet.length());
            password.append(charSet.charAt(randomIndex));
        }

        // Shuffle the password to randomize the order of characters.
        return shuffleString(password.toString());
    }

    // Method to shuffle a string (Fisher-Yates algorithm)
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordManagerGUI().setVisible(true); // Create and show the GUI
            }
        });
    }
}


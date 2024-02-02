package dev.trifanya.swing.content.user.user_form;

import dev.trifanya.model.User;
import dev.trifanya.service.UserService;
import dev.trifanya.swing.MainFrame;
import dev.trifanya.swing.content.ContentLayeredPane;
import dev.trifanya.swing.content.task.task_form.TaskFormPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFormPanel extends JPanel {
    private UserService userService;

    private User currentUser;

    private ContentLayeredPane contentLayeredPane;

    private final int leftMargin = 25;
    private final int rightMargin = 25;
    private final int topMargin = 25;
    private final int bottomMargin = 25;

    private int currentRow = 0;
    private int currentColumn = 0;

    private int ipadx = 20;
    private int ipady = 20;

    private final int labelHorizontalAlignment = SwingConstants.LEFT;

    private JLabel pageTitleLabel;
    private JPanel userFormPanel;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel surnameLabel;
    private JTextField surnameField;

    private JLabel positionLabel;
    private JTextField positionField;

    private JLabel emailLabel;
    private JTextField emailField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JLabel passwordConfirmationLabel;
    private JPasswordField passwordConfirmationField;

    private JButton submitButton;

    public UserFormPanel() {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(600, 400));
        setBackground(MainFrame.firstColor);
        setBorder(new LineBorder(MainFrame.secondColor, 3, true));
    }

    public void init(ContentLayeredPane contentLayeredPane) {
        userService = new UserService();

        this.contentLayeredPane = contentLayeredPane;

        initPageTitleLabel();
        initUserFormPanel();
        initUserNameRow();
        initSurnameRow();
        initPositionRow();
        initEmailRow();
        initPasswordRow();
        initPasswordConfirmationRow();
        initSubmitButton();
    }

    public void initPageTitleLabel() {
        pageTitleLabel = new JLabel("РЕГИСТРАЦИЯ ПОЛЬЗОВАТЕЛЯ");
        MainFrame.setBasicInterface(pageTitleLabel);
        pageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(pageTitleLabel, new GridBagConstraints(
                0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(topMargin, leftMargin, bottomMargin, rightMargin), 30, 20));
    }

    public void initUserFormPanel() {
        userFormPanel = new JPanel();
        userFormPanel.setLayout(new GridBagLayout());
        MainFrame.setBasicInterface(userFormPanel);
        add(userFormPanel, new GridBagConstraints(
                0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, leftMargin, bottomMargin, rightMargin), 0, 0));
    }

    private void initUserNameRow() {
        nameLabel = new JLabel("Имя:");
        MainFrame.setBasicInterface(nameLabel);
        nameLabel.setBorder(null);
        nameLabel.setHorizontalAlignment(labelHorizontalAlignment);
        userFormPanel.add(nameLabel, new GridBagConstraints(currentColumn++, currentRow, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(topMargin, leftMargin, bottomMargin, rightMargin), 10, 10));

        nameField = new JTextField();
        nameField.setColumns(30);
        MainFrame.setBasicInterface(nameField);
        nameField.setCaretColor(MainFrame.secondColor);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        userFormPanel.add(nameField, new GridBagConstraints(currentColumn--, currentRow++, 2, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(topMargin, leftMargin, bottomMargin, rightMargin), ipadx, ipady));
    }

    private void initSurnameRow() {
        surnameLabel = new JLabel("Фамилия:");
        MainFrame.setBasicInterface(surnameLabel);
        surnameLabel.setBorder(null);
        surnameLabel.setHorizontalAlignment(labelHorizontalAlignment);
        userFormPanel.add(surnameLabel, new GridBagConstraints(currentColumn++, currentRow, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));

        surnameField = new JTextField();
        surnameField.setColumns(30);
        MainFrame.setBasicInterface(surnameField);
        surnameField.setHorizontalAlignment(SwingConstants.CENTER);
        userFormPanel.add(surnameField, new GridBagConstraints(currentColumn--, currentRow++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));
    }

    private void initPositionRow() {
        positionLabel = new JLabel("Должность:");
        MainFrame.setBasicInterface(positionLabel);
        positionLabel.setBorder(null);
        positionLabel.setHorizontalAlignment(labelHorizontalAlignment);
        userFormPanel.add(positionLabel, new GridBagConstraints(currentColumn++, currentRow, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));

        positionField = new JTextField();
        positionField.setColumns(30);
        MainFrame.setBasicInterface(positionField);
        positionField.setHorizontalAlignment(SwingConstants.CENTER);
        userFormPanel.add(positionField, new GridBagConstraints(currentColumn--, currentRow++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));
    }

    private void initEmailRow() {
        emailLabel = new JLabel("Email:");
        MainFrame.setBasicInterface(emailLabel);
        emailLabel.setBorder(null);
        emailLabel.setHorizontalAlignment(labelHorizontalAlignment);
        userFormPanel.add(emailLabel, new GridBagConstraints(currentColumn++, currentRow, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));

        emailField = new JTextField();
        emailField.setColumns(30);
        MainFrame.setBasicInterface(emailField);
        emailField.setHorizontalAlignment(SwingConstants.CENTER);
        userFormPanel.add(emailField, new GridBagConstraints(currentColumn--, currentRow++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));
    }

    private void initPasswordRow() {
        passwordLabel = new JLabel("Пароль:");
        MainFrame.setBasicInterface(passwordLabel);
        passwordLabel.setBorder(null);
        passwordLabel.setHorizontalAlignment(labelHorizontalAlignment);
        userFormPanel.add(passwordLabel, new GridBagConstraints(currentColumn++, currentRow, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));

        passwordField = new JPasswordField();
        passwordField.setColumns(30);
        MainFrame.setBasicInterface(passwordField);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        userFormPanel.add(passwordField, new GridBagConstraints(currentColumn--, currentRow++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));
    }

    private void initPasswordConfirmationRow() {
        passwordConfirmationLabel = new JLabel("<html><p style=»text-align: right;»>Подтверждение<br>пароля:</p></html>");
        MainFrame.setBasicInterface(passwordConfirmationLabel);
        passwordConfirmationLabel.setBorder(null);
        passwordConfirmationLabel.setHorizontalAlignment(labelHorizontalAlignment);
        userFormPanel.add(passwordConfirmationLabel, new GridBagConstraints(currentColumn++, currentRow, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));

        passwordConfirmationField = new JPasswordField();
        passwordConfirmationField.setColumns(30);
        MainFrame.setBasicInterface(passwordConfirmationField);
        passwordConfirmationField.setHorizontalAlignment(SwingConstants.CENTER);
        userFormPanel.add(passwordConfirmationField, new GridBagConstraints(currentColumn--, currentRow++, 2, 1, 1, 0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(0, leftMargin, bottomMargin, rightMargin), ipadx, ipady));
    }

    public void initSubmitButton() {
        submitButton = new JButton("ЗАРЕГИСТРИРОВАТЬ ПОЛЬЗОВАТЕЛЯ");
        submitButton.setPreferredSize(new Dimension(340, 40));
        MainFrame.setBasicInterface(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!passwordField.getText().equals(passwordConfirmationField.getText())) {
                    JPopupMenu popupMenu = new JPopupMenu("Пароли не совпадают");
                    UserFormPanel.this.add(popupMenu);
                    popupMenu.setVisible(true);
                }
                User userToSave = new User()
                        .setName(nameField.getText())
                        .setSurname(surnameField.getText())
                        .setPosition(positionField.getText())
                        .setEmail(emailField.getText())
                        .setPassword(passwordField.getText());

                if (currentUser == null) {
                    userService.createNewUser(userToSave);
                    JOptionPane.showMessageDialog(UserFormPanel.this, "Пользователь успешно сохранен", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    clearUserForm();
                } else {
                    userToSave.setId(currentUser.getId());
                    userService.updateUserInfo(userToSave);
                    JOptionPane.showMessageDialog(UserFormPanel.this, "Информация о пользователе успешно обновлена", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        add(submitButton, new GridBagConstraints(
                0, 5, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, leftMargin, bottomMargin, rightMargin), 50, 20));
    }

    /**
     * Заполнение формы данными редактируемой задачи
     */
    public void fillUserForm(User user) {
        nameField.setText(user.getName());
        surnameField.setText(user.getSurname());
        positionField.setText(user.getPosition());
        emailField.setText(user.getEmail());
        passwordField.setText(user.getPassword());
    }

    /**
     * Очистка формы от данных
     */
    public void clearUserForm() {
        nameField.setText("");
        surnameField.setText("");
        positionField.setText("");
        emailField.setText("");
    }

    public void rewriteToUpdate() {
        pageTitleLabel.setText("ИЗМЕНЕНИЕ ПРОФИЛЯ");
        submitButton.setText("СОХРАНИТЬ ИЗМЕНЕНИЯ");
    }

    public void rewriteToCreate() {
        pageTitleLabel.setText("РЕГИСТРАЦИЯ ПОЛЬЗОВАТЕЛЯ");
        submitButton.setText("ЗАРЕГИСТРИОВАТЬ ПОЛЬЗОВАТЕЛЯ");
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (user == null) {
            clearUserForm();
            rewriteToCreate();
            return;
        }
        fillUserForm(user);
        rewriteToUpdate();
    }
}

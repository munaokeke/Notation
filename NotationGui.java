package cmsc204Assign2;
/**
 * Programmer : Daniel Munachimso Okeke
 * Class : CMSC204
 */

import java.text.NumberFormat;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 
public class NotationGui extends Application {

	private TextField infixtxt, infixtxt2, postfixtxt, postfixtxt2;
	private Label infixlbl, infixlbl2,postfixlbl, postfixlbl2,evalanswerlbl, evalanswer;
	private RadioButton infixToPostfix, postfixToInfix;
	private ToggleGroup myToggleGroup;
	private NumberFormat numFormat = NumberFormat.getNumberInstance();
	private Button conversionBtn, evaluatePostBtn, evaluateInBtn, exitBtn;
	private Alert alert = new Alert(AlertType.INFORMATION);
	 
	private class ButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			String infix,postfix,result;
			if (e.getSource() == conversionBtn) {
				try{
					if(infixToPostfix.isSelected())
					{
						infix = infixtxt.getText();
						result = Notation.convertInfixToPostfix(infix);
						postfixlbl.setVisible(true);
						postfixtxt.setVisible(true);
						postfixtxt.setText(result);
					}
					else if(postfixToInfix.isSelected())
					{
						postfix = postfixtxt.getText();
						result = Notation.convertPostfixToInfix(postfix);
						System.out.println("result "+result);
						infixlbl.setVisible(true);
						infixtxt.setVisible(true);
						infixtxt.setText(result);
					}

				}
				catch (InvalidNotationFormatException exception)
				{
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}

			} 
			else if (e.getSource() == evaluatePostBtn) {

				try{
					infixtxt2.setText("");
					String postfixExpr = postfixtxt2.getText();
					double a = Notation.evaluatePostfixExpression(postfixExpr);
					evalanswerlbl.setVisible(true);
					evalanswer.setVisible(true);
					evalanswer.setText(Double.toString(Notation.evaluatePostfixExpression(postfixExpr)));
				}
				catch (InvalidNotationFormatException exception)
				{
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}

			} 
			else if (e.getSource() == evaluateInBtn) {

				try{
					postfixtxt2.setText("");
					String infixExpr = infixtxt2.getText();
					double a = Notation.evaluatePostfixExpression(infixExpr);
					evalanswerlbl.setVisible(true);
					evalanswer.setVisible(true);
					evalanswer.setText(Double.toString(Notation.evaluatePostfixExpression(infixExpr)));
				}
				catch (InvalidNotationFormatException exception)
				{
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}

			} 
			else if (e.getSource() == exitBtn){

				System.exit(0);
			} 
			else {
				if (infixToPostfix.isSelected()){
					postfixtxt.setVisible(false);
					postfixlbl.setVisible(false);
					infixtxt.setText("");
					conversionBtn.setDisable(false);
				} if (!infixToPostfix.isSelected()){
					postfixtxt.setVisible(true);
					postfixlbl.setVisible(true);
				} if (postfixToInfix.isSelected()){
					infixtxt.setVisible(false);
					infixlbl.setVisible(false);
					postfixtxt.setText("");
					conversionBtn.setDisable(false);
				} if (!postfixToInfix.isSelected()){
					infixtxt.setVisible(true);
					infixlbl.setVisible(true);
				}
			}
		}
	}

	@Override
	public void start(Stage stage) {
		
		alert.setTitle("Notatation Utility");
		alert.setHeaderText(null);
		infixlbl = new Label("Infix Expression: ");
		infixlbl2 = new Label("Infix Expression: ");
		postfixlbl = new Label("Postfix Expression:");
		postfixlbl2 = new Label("Postfix Expression:");


		infixtxt = new TextField();
		infixtxt.setMaxWidth(150);
		infixtxt2 = new TextField();
		infixtxt2.setMaxWidth(150);
		postfixtxt = new TextField();
		postfixtxt.setMaxWidth(150);
		postfixtxt2 = new TextField();
		postfixtxt2.setMaxWidth(150);
		
		myToggleGroup = new ToggleGroup();
		infixToPostfix = new RadioButton("Infix to Postfix");
		postfixToInfix = new RadioButton("Postfix to Infix");
		infixToPostfix.setToggleGroup(myToggleGroup);
		postfixToInfix.setToggleGroup(myToggleGroup);
		infixToPostfix.setOnAction(new ButtonEventHandler());
		postfixToInfix.setOnAction(new ButtonEventHandler());

		VBox radioPane1 = new VBox(20);
		radioPane1.getChildren().addAll(infixToPostfix,postfixToInfix);
		VBox infixPane1 = new VBox(20);
		infixPane1.getChildren().addAll(infixlbl,infixtxt);
		VBox postfixPane1 = new VBox(20);
		postfixPane1.getChildren().addAll(postfixlbl,postfixtxt);
		HBox conversionPane1 = new HBox(20);
		conversionPane1.getChildren().addAll(radioPane1,infixPane1,postfixPane1);

		conversionBtn = new Button("Convert");
		conversionBtn.setDisable(true);
		evaluatePostBtn = new Button("Evaluate Postfix");
		evaluateInBtn = new Button("Evaluate Infix");
		exitBtn = new Button("Exit");

		conversionBtn.setOnAction(new ButtonEventHandler());
		evaluatePostBtn.setOnAction(new ButtonEventHandler());
		evaluateInBtn.setOnAction(new ButtonEventHandler());
		exitBtn.setOnAction(new ButtonEventHandler());
		
		HBox buttonPane1 = new HBox(20);
		buttonPane1.getChildren().addAll(exitBtn);
		buttonPane1.setPadding(new Insets(20, 10, 5, 10));
		buttonPane1.setAlignment(Pos.CENTER);

		
		VBox mainPane = new VBox();

		VBox conversionPane = new VBox();
		conversionPane.getChildren().addAll(conversionPane1, conversionBtn);

		TitledPane convertTitlePane = new TitledPane("Notation Conversion",
				conversionPane);
		convertTitlePane.setCollapsible(false);
		convertTitlePane.setMaxWidth(550);
		convertTitlePane.setPadding(new Insets(20, 10, 5, 10));
		

		VBox postfixPane2 = new VBox(20);
		postfixPane2.getChildren().addAll(postfixlbl2,postfixtxt2, evaluatePostBtn);
		postfixPane2.setPadding(new Insets(20, 10, 5, 10));
		

		VBox infixPane2 = new VBox(20);
		infixPane2.getChildren().addAll(infixlbl2,infixtxt2, evaluateInBtn);
		infixPane2.setPadding(new Insets(20, 10, 5, 10));
		
		evalanswerlbl = new Label("Answer: ");
		evalanswerlbl.setVisible(false);
		evalanswer = new Label();
		evalanswer.setVisible(false);
		
		VBox evalanswerPane = new VBox();
		evalanswerPane.getChildren().addAll(evalanswerlbl, evalanswer);
	
		HBox evaluationPane = new HBox();
		evaluationPane.getChildren().addAll(postfixPane2, infixPane2, evalanswerPane);
		
		TitledPane evalTitlePane = new TitledPane("Notation Evaluation",
				evaluationPane);
		evalTitlePane.setCollapsible(false);
		evalTitlePane.setMaxWidth(550);
		evalTitlePane.setPadding(new Insets(20, 10, 5, 10));


		mainPane.getChildren().addAll(convertTitlePane, evalTitlePane,
				buttonPane1);
		Scene scene = new Scene(mainPane, 550, 430);
		stage.setScene(scene);

		stage.setTitle("Notation Utility ");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
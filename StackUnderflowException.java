package cmsc204Assign2;
/***********************************************************************
Class: CMSC204 CRN 22378
Program: Assignment # 2
Instructor: Professor Alexander
Description: Stack underflow exception class
Due: 09/30/2020
I pledge that I have completed the programming assignment independently.
I have not copied the code from a student or any source.
Anthony Liu
************************************************************************/
/**
* Thrown is stack is empty
* @author Anthony Liu
*
*/
public class StackUnderflowException extends Exception {

	StackUnderflowException(){
		super("Stack is empty");
	}
}
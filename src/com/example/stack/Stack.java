package com.example.stack;

public class Stack {
    //function that returns operator with the highest precedence
    static int Precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        //the string that we want to convert to postfix
        String expression="a+b*(c-d)^e*f";
        //calling the function that does the conversion with the string as the parameter and printing the results
        System.out.println("Postfix expression of " +expression+ " = "+infixToPostfixConverter(expression));
    }
    public static String infixToPostfixConverter(String expression){
        //  initializing answer as empty string
        String answer= "";

        //initializing an empty stack
        java.util.Stack<Character> myStack=new java.util.Stack<>();

        //a loop that traverses the string character by character
        for(int j=0;j<expression.length();++j){

            char character=expression.charAt(j);

            //if scanned character is an operand, add it to answer

            if (Character.isLetterOrDigit(character))
                answer+=character;

            //if scanned character is  an opening bracket ('(') push it to the stack
            else if (character=='(')

                myStack.push(character);

            //if scanned character is  a closing bracket (')') pop operands from stack and push to output until an opening bracket '(' is found
            else if (character==')') {

                while (!myStack.isEmpty() && myStack.peek() != '(')
                    answer += myStack.pop();

                myStack.pop();
            }
            //an operator is encountered
            else{
                //calling the precedence function to  check the precedence of an encountered operator
                while (!myStack.isEmpty() && Precedence(character)<=Precedence(myStack.peek())){
                    answer+=myStack.pop();
                }
                myStack.push(character);

            }
            //printing current state of stack after reading each character
            String currentStack=new String(String.valueOf(myStack));
            System.out.println("State of stack after processing "+expression.charAt(j)+ "" +currentStack);
            System.out.println("Partial postfix expression : "+answer);
        }
        while (!myStack.isEmpty()){
            if (myStack.peek()=='(')
                return "Expression is not valid !!";
            answer+=myStack.pop();
        }
        return answer;

    }
}

package hackerrank.other;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Muneer on 17-01-2017.
 */
public class ExpressionEvaluation {
    final static String PRECEDENCE_STRING = "+-*/";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        sc.nextLine();
        for (int it = 0; it < t; it++) {

            String infix = sc.nextLine();

            String postfix = getPostfixFromInfix(infix);
            System.out.println(postfix);

            Integer res = evaluatePostfix(postfix);
            if (res != null)
                System.out.println(res);
            else
                System.out.print("Invalid Expression");
        }
    }

    private static Integer evaluatePostfix(String postfix) {
        Integer res;
        int n = postfix.length();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            if (ch == ' ') {
                continue;
            } else if (isOperator(ch)) {
                if (i == n - 1 || postfix.charAt(i + 1) == ' ' || PRECEDENCE_STRING.contains(String.valueOf(postfix.charAt(i + 1)))) {
                    int b = stack.pop();
                    int a = stack.pop();
                    Integer c = applyOperator(ch, a, b);
                    if (c == null)
                        return null;
                    stack.push(c);
                }
            } else {
                StringBuilder num = new StringBuilder();
                do {
                    num.append(postfix.charAt(i++));
                } while (i < n && Character.isDigit(postfix.charAt(i)));
                stack.push(Integer.parseInt(num.toString()));
                i--;
            }
        }

        res = stack.pop();
        return res;
    }


    private static String getPostfixFromInfix(String infix) {
        StringBuilder res = new StringBuilder();
        Stack<String> stack = new Stack<>();

        boolean isOperatorReq = false;
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (ch == ' ') {
                continue;
            } else if (ch == '(') {
                stack.push(String.valueOf(ch));
            } else if (isOperatorReq && isOperator(ch)) {
                while (!stack.isEmpty() && !stack.peek().equals("(") && hasHigherPrecedence(stack.peek(), String.valueOf(ch))) {
                    res.append(stack.pop());
                    res.append(" ");
                }
                stack.push(String.valueOf(ch));
                isOperatorReq = false;
            } else if (ch == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    res.append(stack.pop());
                    res.append(" ");
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                StringBuilder num = new StringBuilder();
                if (ch == '-') {
                    num.append(ch);
                    i++;
                }
                while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                    num.append(infix.charAt(i++));
                }
                res.append(num);
                res.append(" ");
                i--;
                isOperatorReq = true;
            }
        }

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.toString();
    }

    private static Integer applyOperator(char operator, int a, int b) {
        int res = 0;
        switch (operator) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            case '/':
                if (b == 0)
                    return null;
                res = a / b;
                break;
        }
        return res;
    }

    private static boolean hasHigherPrecedence(String peek, String ch) {
        int peekIdx = PRECEDENCE_STRING.indexOf(peek);
        int chIdx = PRECEDENCE_STRING.indexOf(ch);
        if (peekIdx >= chIdx)
            return true;
        if ((peek.equals("+") && ch.equals("-")) || (peek.equals("-") && ch.equals("+")))
            return true;
        if ((peek.equals("*") && ch.equals("/")) || (peek.equals("/") && ch.equals("*")))
            return true;
        return false;
    }

    private static boolean isOperator(char ch) {
        if (PRECEDENCE_STRING.indexOf(ch) >= 0)
            return true;
        return false;
    }
}

package spoj;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Muneer on 15-01-2017.
 */
public class TransformTheExpression {

    final static String PRECEDENCE_STRING = "+-*/^";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int it = 0; it < t; it++) {

            String s = sc.next();

            StringBuilder res = new StringBuilder();
            Stack<Character> stack = new Stack<>();

            for(char ch : s.toCharArray()) {
                if(isOperand(ch)) {
                    res.append(ch);
                } else if(ch == '(') {
                    stack.push(ch);
                } else if(isOperator(ch)) {
                    while (!stack.isEmpty() && stack.peek() != '(' && hasHigherPrecendence(stack.peek(), ch)) {
                        res.append(stack.pop());
                    }
                    stack.push(ch);
                } else if(ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        res.append(stack.pop());
                    }
                    if(!stack.isEmpty()) {
                        stack.pop();
                    }
                }
            }

            while (!stack.isEmpty()) {
                res.append(stack.pop());
            }

            System.out.println(res.toString());
        }
    }

    private static boolean hasHigherPrecendence(Character peek, char ch) {
        int peekIdx = PRECEDENCE_STRING.indexOf(peek);
        int chIdx = PRECEDENCE_STRING.indexOf(ch);
        if(peekIdx > chIdx)
            return true;
        return false;
    }

    private static boolean isOperator(char ch) {
        if(PRECEDENCE_STRING.indexOf(ch) >= 0)
            return true;
        return false;
    }

    private static boolean isOperand(char ch) {
        if(PRECEDENCE_STRING.indexOf(ch) >= 0 || ch == '(' || ch == ')')
            return false;
        return true;
    }
}

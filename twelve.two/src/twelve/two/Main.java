package twelve.two;

import java.util.Stack;

public class Main {
    
    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return 0;
    }
    
    private static boolean validate(String expr) {
        int count = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
    
    private static String toPostfix(String infix) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            
            if (c == ' ') continue;
            
            if (Character.isDigit(c)) {
                while (i < infix.length() && (Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    result.append(infix.charAt(i++));
                }
                i--;
                result.append(' ');
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(' ');
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c) && c != '^') {
                    result.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }
        
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(' ');
        }
        
        return result.toString().trim();
    }
    
    private static double evaluate(String postfix) {
        Stack<Double> stack = new Stack<>();
        
        for (String token : postfix.split("\\s+")) {
            if (token.matches("[+\\-*/^]")) {
                double b = stack.pop();
                double a = stack.pop();
                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                    case '^': stack.push(Math.pow(a, b)); break;
                }
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        
        return stack.pop();
    }
    
    public static void main(String[] args) {
        String[] expressions = {
            "(3 + 4) * 2 / (1 - 5)",
            "(2 + 3) + 5 * (4 - 1)",
            "10 + 2 * 6"
        };
        
        for (String expr : expressions) {
            System.out.println("Input: " + expr);
            
            if (!validate(expr)) {
                System.out.println("Invalid expression: Unbalanced parentheses\n");
                continue;
            }
            
            String postfix = toPostfix(expr);
            System.out.println("Postfix: " + postfix);
            System.out.println("Result: " + evaluate(postfix));
            System.out.println();
        }
    }
}
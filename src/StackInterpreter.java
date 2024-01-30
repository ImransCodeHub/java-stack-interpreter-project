import java.util.*;

public class StackInterpreter {
    private List<String> words;
    private Stack<String> stack;
    private Map<String, List<String>> wordDefinitions;

    public StackInterpreter(List<String> words) {
        this.words = words;
        this.stack = new Stack<>();
        this.wordDefinitions = new HashMap<>();
    }

    public void executeProgram() {
        if (words.isEmpty()) {
            throw new IllegalArgumentException("Error: No words to interpret");
        }
        for (String word : words) {
            if (isNumber(word)) {
                stack.push(word);

            } else if (word.equals("'")) {
                try {
                    handleQuotes(word);
                } catch (Exception e) {
                    System.out.println("Quote error: " + e.getMessage());
                }

            } else if (word.equals(":")) {
                try {
                    handleDefinitions(word);
                } catch (Exception e) {
                    System.out.println("Definition error: " + e.getMessage());
                }

            } else if (isStackOperation(word)) {
                try {
                    handleStackOperation(word);
                } catch (Exception e) {
                    System.out.println("StackOperation error: " + e.getMessage());
                }

            } else if (isIOOperation(word)) {
                try {
                    handleIOOperation(word);
                } catch (Exception e) {
                    System.out.println("IOOperation error: " + e.getMessage());
                }

            } else {
                throw new RuntimeException("Error: Unknown word '" + word + "'");
            }
        }
    }

    //Number
    private static boolean isNumber(String word) {
        try {
            Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void handleQuotes(String word) {
        if (word.startsWith("'") && word.endsWith("'")) {
            StringBuilder quoteString = new StringBuilder();
            while (!stack.isEmpty() && !stack.peek().equals("'")) {
                quoteString.insert(0, stack.pop() + " ");
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
            stack.push(quoteString.toString().trim());
        } else if (word.equals("out")) {
            if (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
        } else {
            stack.push(word);
        }
    }

    private void handleDefinitions(String word) {
        if (wordDefinitions.containsKey(word)) {
            if (stack.isEmpty()) {
                throw new RuntimeException("Error: No word provided for definition");
            }

            String newWord = stack.pop();

            List<String> definition = new ArrayList<>();
            while (!stack.isEmpty()) {
                definition.add(0, stack.pop());
            }

            wordDefinitions.put(newWord, definition);
        }
    }


    //IOOperation
    private static boolean isIOOperation(String word) {
        if (word.equals("in") || word.equals("out")) {
            return true;
        }
        return false;
    }
    private void handleIOOperation(String word) {
        if (word.equals("in")) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            stack.push(input);
        } else if (word.equals("out")) {
            if (!stack.isEmpty()) {
                System.out.println(stack.peek());
            }
        }
    }

    //StackOperation
    private static boolean isStackOperation(String word) {
        if (word.equals("dup") ||
                word.equals("swap") ||
                word.equals("pop") ||
                word.equals("+") ||
                word.equals("*") ||
                word.equals("-")) {
            return true;
        }
        return false;
    }
    private void handleStackOperation(String word) {
        if (word.equals("dup")) {
            if (!stack.isEmpty()) {
                String top = stack.peek();
                stack.push(top);
            } else {
                throw new RuntimeException("Error: Stack is empty can't dup");
            }
        } else if (word.equals("swap")) {
            if (stack.size() >= 2) {
                String top1 = stack.pop();
                String top2 = stack.pop();
                stack.push(top1);
                stack.push(top2);
            } else {
                throw new RuntimeException("Error: At least two numbers are required to swap");
            }
        } else if (word.equals("pop")) {
            if (!stack.isEmpty()) {
                stack.pop();
            } else {
                throw new RuntimeException("Error: Stack is empty can't pop");
            }
        } else if (word.equals("+")) {
            if (stack.size() >= 2) {
                String num1 = stack.pop();
                String num2 = stack.pop();
                if (isNumeric(num1) && isNumeric(num2)) {
                    int result = Integer.parseInt(num1) + Integer.parseInt(num2);
                    stack.push(String.valueOf(result));
                } else {
                    stack.push(num2 + num1);
                }
            } else {
                throw new RuntimeException("Error: At least two numbers are required to add");
            }
        } else if (word.equals("*")) {
            if (stack.size() >= 2) {
                String num1 = stack.pop();
                String num2 = stack.pop();
                if (isNumeric(num1) && isNumeric(num2)) {
                    int result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    stack.push(String.valueOf(result));
                } else {
                    int index = num2.indexOf(num1);
                    if (index >= 0) {
                        String result = num2.substring(index);
                        stack.push(result);
                    } else {
                        stack.push(num2);
                    }
                }
            } else {
                throw new RuntimeException("Error: At least two numbers are required to multiply");
            }
        } else if (word.equals("-")) {
            if (!stack.isEmpty()) {
                String top = stack.pop();
                if (isNumeric(top)) {
                    int num = Integer.parseInt(top);
                    stack.push(String.valueOf(-num));
                } else {
                    StringBuilder reversed = new StringBuilder(top).reverse();
                    stack.push(reversed.toString());
                }
            } else {
                throw new RuntimeException("Error: Stack is empty can't negate");
            }
        } else {
            throw new RuntimeException("Unknown stack operation: " + word);
        }
    }

    //isNumeric
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+");
    }
}
package vn.tcx.zkoss.tree.calc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class DTPoland {

	Stack<String> stack = new Stack<String>();
    ArrayList<String> list = new ArrayList<String>();

    public Double changeToSuffix(String str) {
        String s[] = parseInputString(str);
        String operator = null;
        for (int i = 0; i < s.length; i++) {
            if (checkNumber(s[i])) {
                list.add(s[i]);
            } else {
                if (s[i].equals("("))
                    stack.push(s[i]);
                else if (s[i].equals("+")) {
                    while (true) {
                        if (stack.size() > 0) {
                            operator = (String) stack.peek();
                            if (operator.equals("-")||operator.equals("+")
                                    || operator.equals("*")
                                    || operator.equals("/")) {
                                list.add((String)stack.pop());
                            } else {
                                stack.push(s[i]);
                                break;
                            }
                        } else {
                            stack.push(s[i]);
                            break;
                        }
                    }
                } else if (s[i].equals("-")) {
                    while (true) {
                        if (stack.size() > 0) {
                            operator = (String) stack.peek();
                            if (operator.equals("-")||operator.equals("+") || operator.equals("*")
                                    || operator.equals("/")
                                    ) {
                                list.add((String)stack.pop());
                            } else {
                                stack.push(s[i]);
                                break;
                            }
                        } else {
                            stack.push(s[i]);
                            break;
                        }
                    }
                } else if (s[i].equals("*")) {
                    while (true) {
                        if (stack.size() > 0) {
                            operator = (String) stack.peek();
                            if (operator.equals("*")||operator.equals("/")) {
                                list.add((String)stack.pop());
                            } else {
                                stack.push(s[i]);
                                break;
                            }
                        } else {
                            stack.push(s[i]);
                            break;
                        }
                    }
                } else if (s[i].equals("/")) {
                    while (true) {
                        if (stack.size() > 0) {
                            operator = (String) stack.peek();
                            if (operator.equals("/")||operator.equals("*")) {
                                list.add((String)stack.pop());
                            } else {
                                stack.push(s[i]);
                                break;
                            }
                        } else {
                            stack.push(s[i]);
                            break;
                        }
                    }
                } else if (s[i].equals(")")) {
                    while (stack.size() > 0) {
                        operator = (String) stack.pop();
                        if (!operator.equals("("))
                            list.add(operator);
                        else
                            break;
                    }
                }
                }
            }
            while (stack.size() > 0) {
                operator = (String) stack.pop();
                    list.add(operator);
            }
        return calculate(list);
    }
    public boolean checkNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public double calculate(ArrayList<String> list){
        LinkedList<String> tmpList=new LinkedList<String>();
        for(String str:list){
            tmpList.add(str);
        }

        while(tmpList.size()>1){
            int i=0;
            double result=0;
            for(;i<tmpList.size();i++){
                String oper=tmpList.get(i);
                if(!checkNumber(oper)){
                    double num1=Double.parseDouble(tmpList.get(i-2));
                    double num2=Double.parseDouble(tmpList.get(i-1));


                    if(oper.equals("+")){
                        result=num1+num2;
                    }else if(oper.equals("-")){
                        result=num1-num2;
                    }else if(oper.equals("*")){
                        result=num1*num2;
                    }else{
                        result=(double)num1/num2;
                    }

                    break;
                }
            }

            for(int j=0;j<3;j++){
                tmpList.remove(i-2);
            }

            //System.out.println(tmpList);

            tmpList.add(i-2, String.valueOf(result));
            //System.out.println(tmpList);
        }

        return Double.parseDouble(tmpList.getFirst());
    }

    private boolean isOperator(char c){
        if(c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')')
            return true;
        return false;
    }

    private String[] parseInputString(String input){
        StringBuilder strBd=new StringBuilder();

        for(int i=0;i<input.length();i++){
            if(isOperator(input.charAt(i))){
                strBd.append(" ");
                strBd.append(input.charAt(i));
            }else{
                if(i>0 && isOperator(input.charAt(i-1))){
                    strBd.append(" ");
                }
                strBd.append(input.charAt(i));
            }
        }

        String r=strBd.toString();
        return r.split(" ");
    }
    public static void main(String []args)
    {
     String s;
     Scanner input=new Scanner(System.in);
     System.out.println("Nhap bieu thuc : ");
     s=input.nextLine();
     DTPoland p = new DTPoland();
     System.out.println("Ket qua : "+p.changeToSuffix(s));
    }
}
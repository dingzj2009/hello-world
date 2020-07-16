package question.bank.validnumber;

import java.util.ArrayList;

/**
 * 难度：困难
 * 解法二中自动机的应用，会使得自己的思路更清晰。
 * 而解法三中，作者提出的对设计模式的应用，使自己眼前一亮，虽然代码变多了，但是维护性，扩展性变的很强了。
 * 比如，题目新增了一种情况，"0x123" 16 进制也算是合法数字。
 * 这样的话，解法一和解法二就没什么用了，完全得重新设计。
 * 但对于解法三，我们只需要新增一个类，专门判断这种情况，然后加到执行者的数组里就够了，很棒！
 */
public class ValidNumber {
	
	public static void main(String[] args){
		System.out.println("-----直接法测试-----");
		System.out.println("123 is number:" + isNumber_1("123"));
		System.out.println("0123 is number:" + isNumber_1("0123"));
		System.out.println("123.5 is number:" + isNumber_1("123.5"));
		System.out.println("1.23e10 is number:" + isNumber_1("1.23e10"));
		System.out.println("1.0e4.5 is number:" + isNumber_1("1.0e4.5"));
		System.out.println("1a3 is number:" + isNumber_1("1a3"));
		System.out.println(".1 is number:" + isNumber_1(".1"));
		System.out.println("2e0 is number:" + isNumber_1("2e0"));
		System.out.println("    is number:" + isNumber_1("   "));
		System.out.println("005047e+6 is number:" + isNumber_1("005047e+6"));
		System.out.println("------------------");
		System.out.println("-----自动机测试-----");
		System.out.println("123 is number:" + isNumber_2("123"));
		System.out.println("0123 is number:" + isNumber_2("0123"));
		System.out.println("123.5 is number:" + isNumber_2("123.5"));
		System.out.println("1.23e10 is number:" + isNumber_2("1.23e10"));
		System.out.println("1.0e4.5 is number:" + isNumber_2("1.0e4.5"));
		System.out.println("1a3 is number:" + isNumber_2("1a3"));
		System.out.println(".1 is number:" + isNumber_2(".1"));
		System.out.println("2e0 is number:" + isNumber_2("2e0"));
		System.out.println("    is number:" + isNumber_2("   "));
		System.out.println("005047e+6 is number:" + isNumber_2("005047e+6"));
		System.out.println("------------------");
		System.out.println("-----责任链模式测试-----");
		NumberValidate nv = new NumberValidator(); 
		System.out.println("123 is number:" + nv.validate("123"));
		System.out.println("0123 is number:" + nv.validate("0123"));
		System.out.println("123.5 is number:" + nv.validate("123.5"));
		System.out.println("1.23e10 is number:" + nv.validate("1.23e10"));
		System.out.println("1.0e4.5 is number:" + nv.validate("1.0e4.5"));
		System.out.println("1a3 is number:" + nv.validate("1a3"));
		System.out.println(".1 is number:" + nv.validate(".1"));
		System.out.println("2e0 is number:" + nv.validate("2e0"));
		System.out.println("    is number:" + nv.validate("   "));
		System.out.println("005047e+6 is number:" + nv.validate("005047e+6"));
		System.out.println("------------------");
	}
	
	/**
	 * 1:直接法
	 * 时间复杂度：O（n）；空间复杂度：O（1）
	 * @param s
	 * @return
	 */
	public static boolean isNumber_1(String s) {
	    s = s.trim();
	    boolean pointSeen = false;
	    boolean eSeen = false;
	    boolean numberSeen = false;
	    boolean numberAfterE = true;
	    for(int i=0; i<s.length(); i++) {
	        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
	            numberSeen = true;
	            numberAfterE = true;
	        } else if(s.charAt(i) == '.') {
	            if(eSeen || pointSeen) {
	                return false;
	            }
	            pointSeen = true;
	        } else if(s.charAt(i) == 'e') {
	            if(eSeen || !numberSeen) {
	                return false;
	            }
	            numberAfterE = false;
	            eSeen = true;
	        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
	            if(i != 0 && s.charAt(i-1) != 'e') {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    }

	    return numberSeen && numberAfterE;
	}
	
	/**
	 * 2:自动机
	 * 从自动机图看，从 0 开始总共有 9 个状态，橙色代表可接受状态，也就是表示此时是合法数字。总共有四大类输入，数字，小数点，e 和 正负号。我们只需要将这个图实现就够了。
	 * 时间复杂度：O（n）；空间复杂度：O（1）
	 * @param s
	 * @return
	 */
	public static boolean isNumber_2(String s) {
	    int state = 0; 
	    s = s.trim();//去除头尾的空格
	    //遍历所有字符，当做输入
	    for (int i = 0; i < s.length(); i++) {
	        switch (s.charAt(i)) {
	             //输入正负号
	            case '+':
	            case '-':
	                if (state == 0) {
	                    state = 1;
	                } else if (state == 4) {
	                    state = 6;
	                } else {
	                    return false;
	                }
	                break;
	            //输入数字
	            case '0':
	            case '1':
	            case '2':
	            case '3':
	            case '4':
	            case '5':
	            case '6':
	            case '7':
	            case '8':
	            case '9':
	                //根据当前状态去跳转
	                switch (state) {
	                    case 0:
	                    case 1:
	                    case 2:
	                        state = 2;
	                        break;
	                    case 3:
	                        state = 3;
	                        break;
	                    case 4:
	                    case 5:
	                    case 6:
	                        state = 5;
	                        break;
	                    case 7:
	                        state = 8;
	                        break;
	                    case 8:
	                        state = 8;
	                        break;
	                    default:
	                        return false;
	                }
	                break;
	            //小数点
	            case '.':
	                switch (state) {
	                    case 0:
	                    case 1:
	                        state = 7;
	                        break;
	                    case 2:
	                        state = 3;
	                        break;
	                    default:
	                        return false;
	                }
	                break;
	            //e
	            case 'e':
	                switch (state) {
	                    case 2:
	                    case 3:
	                    case 8:
	                        state = 4;
	                        break;
	                    default:
	                        return false;
	                }
	                break;
	            default:
	                return false;

	        }
	    }
	    //橙色部分的状态代表合法数字
	    return state == 2 || state == 3 || state == 5 || state == 8;
	}

}

/**
 * 每个类都实现这个接口
 */
interface NumberValidate { 
  boolean validate(String s);
}

/**
 * 定义一个抽象类，用来检查一些基础的操作，是否为空，去掉首尾空格，去掉 +/-
 * doValidate 交给子类自己去实现
 */
abstract class  NumberValidateTemplate implements NumberValidate{

	public boolean validate(String s) {
	    if (checkStringEmpty(s)) {
	    	return false;
	    }
	
	    s = checkAndProcessHeader(s);
	
	    if (s.length() == 0) {
	        return false;
	    }
	
	    return doValidate(s);
	}

	private boolean checkStringEmpty(String s) {
		if (s.equals("")) {
			return true;
		}

		return false;
	}

	private String checkAndProcessHeader(String value) {
		value = value.trim();

		if (value.startsWith("+") || value.startsWith("-")) {
			value = value.substring(1);
		}

		return value;
	}

	protected abstract boolean doValidate(String s);
}

/**
 * 实现 doValidate 判断是否是整数
 */
class IntegerValidate extends NumberValidateTemplate{

	protected boolean doValidate(String integer) {
		for (int i = 0; i < integer.length(); i++) {
			if(Character.isDigit(integer.charAt(i)) == false) {
				return false;
			}
		}

		return true;
	}
}

/**
 * 实现 doValidate 判断是否是科学计数法
 */
class SienceFormatValidate extends NumberValidateTemplate{

	protected boolean doValidate(String s) {
		s = s.toLowerCase();
		int pos = s.indexOf("e");
		if (pos == -1) {
			return false;
		}

		if (s.length() == 1) {
			return false;
		}

		String first = s.substring(0, pos);
		String second = s.substring(pos+1, s.length());

		if (validatePartBeforeE(first) == false || validatePartAfterE(second) == false) {
			return false;
		}

		return true;
	}

	private boolean validatePartBeforeE(String first) {
		if (first.equals("") == true) {
			return false;
		}

		if (checkHeadAndEndForSpace(first) == false) {
			return false;
		}

		NumberValidate integerValidate = new IntegerValidate();
		NumberValidate floatValidate = new FloatValidate();
		if (integerValidate.validate(first) == false && floatValidate.validate(first) == false) {
			return false;
		}

		return true;
	}

	private boolean checkHeadAndEndForSpace(String part) {
		if (part.startsWith(" ") || part.endsWith(" ")) {
			return false;
		}

		return true;
	}

	private boolean validatePartAfterE(String second) {
		if (second.equals("") == true) {
			return false;
		}

		if (checkHeadAndEndForSpace(second) == false) {
			return false;
		}

		NumberValidate integerValidate = new IntegerValidate();
		if (integerValidate.validate(second) == false) {
			return false;
		}

		return true;
	}
}

/**
 * 实现 doValidate 判断是否是小数
 */
class FloatValidate extends NumberValidateTemplate{

	protected boolean doValidate(String floatVal) {
		int pos = floatVal.indexOf(".");
		if (pos == -1) {
			return false;
		}

		if (floatVal.length() == 1) {
			return false;
		}

		String first = floatVal.substring(0, pos);
		String second = floatVal.substring(pos + 1, floatVal.length());

		if (checkPart(first) == true && checkPart(second) == true) {
			return true;
		}

		return false;
	}

	private boolean checkPart(String first) {
		if (first.equals("") == false && checkNumPart(first) == false) {
			return false;
		}

		return true;
	}

	private boolean checkNumPart(String part) {
		
		NumberValidate nv = new IntegerValidate();
		if (nv.validate(part) == false) {
			return false;
		}

		return true;
	}
}

/**
 * 定义一个执行者，我们把之前实现的各个类加到一个数组里，然后依次调用
 * 解法二看起来已经很清晰明了了，只需要把状态图画出来，然后实现代码就很简单了。但是缺点是，如果状态图少考虑了东西，再改起来就会很麻烦。
 * 
 * 这时利用责任链的设计模式，会使得写出的算法扩展性以及维护性更高。
 * 这里用到的思想就是，每个类只判断一种类型。比如判断是否是正数的类，判断是否是小数的类，判断是否是科学计数法的类，这样每个类只关心自己的部分，出了问题很好排查，而且互不影响。
 */
class NumberValidator implements NumberValidate {

	private ArrayList<NumberValidate> validators = new ArrayList<NumberValidate>();

	public NumberValidator() {
		addValidators();
	}

	private void addValidators() {
		NumberValidate nv = new IntegerValidate();
		validators.add(nv);

		nv = new FloatValidate();
		validators.add(nv);

//		nv = new HexValidate();
//		validators.add(nv);

		nv = new SienceFormatValidate();
		validators.add(nv);
	}

	@Override
	public boolean validate(String s) {
		for (NumberValidate nv : validators) {
			if (nv.validate(s) == true) {
				return true;
			}
		}

		return false;
	}

}


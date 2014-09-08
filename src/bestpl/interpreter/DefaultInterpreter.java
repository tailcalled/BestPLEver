package bestpl.interpreter;

import java.util.List;

public class DefaultInterpreter extends Interpreter {
	
	public DefaultInterpreter() {
		put(string("+"), new BPLOperator((List<BPLValue> params) -> {
			float sum = 0;
			for (BPLValue v: params) {
				sum += Double.parseDouble(v.toString());
			}
			return real(sum);
		}));
		put(string("*"), new BPLOperator((List<BPLValue> params) -> {
			float product = 1;
			for (BPLValue v: params) {
				product *= Double.parseDouble(v.toString());
			}
			return real(product);
		}));
		put(string("print"), new BPLOperator((List<BPLValue> params) -> {
			for (BPLValue v: params) {
				System.out.print(v);
			}
			System.out.println();
			return null;
		}));
	}
	
}

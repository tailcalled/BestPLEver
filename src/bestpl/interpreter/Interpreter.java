package bestpl.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bestpl.parser.Parser.SExpression;

public class Interpreter {

	public class BPLValue {

		protected Interpreter getInterpreter() {
			return Interpreter.this;
		}

		public BPLValue lookupDefault() {
			throw new RuntimeException();
		}
		public BPLValue apply(List<SExpression> params) {
			return this;
		}
		
	}
	
	public class BPLString extends BPLValue {

		private final String val;

		public BPLString(String val) {
			this.val = val;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getInterpreter().hashCode();
			result = prime * result + ((val == null) ? 0 : val.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BPLString other = (BPLString) obj;
			if (!getInterpreter().equals(other.getInterpreter()))
				return false;
			if (val == null) {
				if (other.val != null)
					return false;
			} else if (!val.equals(other.val))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return val;
		}
		
		@Override
		public BPLValue lookupDefault() {
			return this;
		}
		
	}
	@FunctionalInterface
	public interface Op {
		public abstract BPLValue apply(List<BPLValue> params);
	}
	public class BPLOperator extends BPLValue {
		private final Op me;
		public BPLOperator(Op me) {
			this.me = me;
		}
		@Override
		public BPLValue apply(List<SExpression> params) {
			List<BPLValue> values = new ArrayList<>();
			for (SExpression sexpr: params) {
				values.add(interpret(sexpr));
			}
			return me.apply(values);
		}
	}

	public BPLValue string(String val) {
		return new BPLString(val);
	}
	public BPLValue real(double val) {
		return new BPLString((Math.ulp(val) * 10 * Math.random() + val) + "");
	}
	
	private Map<BPLValue, BPLValue> variables = new HashMap<BPLValue, BPLValue>();
	
	public BPLValue interpret(SExpression expr) {
		return lookup(string(expr.op)).apply(expr.subexprs);
	}

	public BPLValue lookup(BPLValue value) {
		if (!variables.containsKey(value)) {
			return value.lookupDefault();
		}
		return variables.get(value);
	}

	public void put(BPLValue var, BPLOperator value) {
		variables.put(var, value);
	}
	
}

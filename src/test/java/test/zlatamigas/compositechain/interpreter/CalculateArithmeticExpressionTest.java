package test.zlatamigas.compositechain.interpreter;

import com.zlatamigas.compositechain.interpreter.CalculateArithmeticExpression;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateArithmeticExpressionTest {

    @DataProvider(name = "arithmeticExpressionPr")
    public Object[][] createDataForAvg() {
        return new Object[][]{
                {"-3-5", -8.0},
                {"6*9/(3+3)", 9.0},
                {"5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1)", -873.3293064876957},
                {"(-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7", -9.567701863354037},
                {"(7+5*12*(2+5-2-71))/10", -395.3},
                {"2+2*2", 6.0},
                {"(2+2)*2", 8.0},
                {"-12", -12.0}
        };
    }

    @Test(dataProvider = "arithmeticExpressionPr")
    public void testCalculate(String arithmeticExpression, Double expected) {
        CalculateArithmeticExpression calculator = new CalculateArithmeticExpression(arithmeticExpression);
        double actual = calculator.calculate();

        assertEquals(actual, expected);
    }
}
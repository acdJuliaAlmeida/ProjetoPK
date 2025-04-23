package up.eti;
public class Main {
    public static void main(String[] args) {

    }
    public double calcularDano(double level, double critical, double power, double attack, double defense, double stab, double type1, double type2,double random){

        return ((((((2*level*critical)/5)+2)*power*attack*defense)/50)+2)*stab*type1*type2*random;

    }

}
public class Main {
    public static void main(String[] args)
    {
        Paint menu=new Paint();

        Pretreat str=new Pretreat();

        Compute one=new Compute(str.output());

        System.out.print(one.output());
    }
}

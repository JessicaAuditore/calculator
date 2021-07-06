import java.util.Scanner;
public class Pretreat {
    private String str;
    private Node[] array;
    private int max=1;

    Pretreat()
    {
        input();
        handle();
    }

    private void input()
    {
        Scanner reader=new Scanner(System.in);
        System.out.println("请输入要计算的表达式");
        str=reader.nextLine();
    }

    private void handle()
    {
        treatsome();
        treatminus();
    }

    private void treatsome()
    {
        str=str.replace(" ","");
        str=str.replace("sin","s");
        str=str.replace("cos","c");
        str=str.replace("tan","t");
    }

    //处理负号
    private void treatminus()
    {
        if(str.charAt(0)=='-') str='0'+str;
        for(int i=1;i<str.length();i++)
        {
            if(str.charAt(i)=='-'&&!(str.charAt(i+1)>='0'&&str.charAt(i+1)<='9'))
            {
                str=str.substring(0,i+1)+'0'+str.substring(i+1,str.length());
            }
        }
    }


    public String output()
    {
        return this.str;
    }
}

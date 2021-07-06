public class Compute {
    private String str;
    private Node[] array;
    private int max=1;  //max为括号的最高等级

    Compute(String a)
    {
        this.str=a;
        core();
    }

    //核心
    private void core()
    {
        Node[] ss=getnoede(this.str);
        while(this.max>0)
        {
            for(int i=0;i<str.length();i++)
            {
                String s="";
                if(ss[i].level==max&&ss[i].ch=='(')
                {
                    int ii=i+1; //i为左括号的位置 ii为右括号的位置
                    for(int j=i+1;j<str.length();j++)
                    {
                        if(ss[j].level==max&&ss[j].ch==')') {
                            ii = j;
                            break;
                        }
                    }

                    for(int j=i+1;j<=ii-1;j++)
                    {
                        s=s+str.charAt(j);
                    }
                    double w=getans(s);
                    if(w<0) str=str.substring(0,i)+"0"+w+str.substring(ii+1);
                    if(w>=0) str=str.substring(0,i)+w+str.substring(ii+1);
                    ss=getnoede(str); //str发生变化，重新赋值给ss
                }
            }
            max--;
        }
    }

    //接受字符串s，转化为对应的数组并给括号赋等级
    private Node[] getnoede(String s)
    {
        Node[] a=new Node[s.length()];
        for(int i=0;i<s.length();i++)
        {
            a[i]=new Node();
        }
        for(int i=0;i<s.length();i++)
        {
            a[i].ch=s.charAt(i);
            a[i].level=0;
        }

        int e=1;
        for(int i=0;i<s.length();i++)
        {
            if(str.charAt(i)=='(')
            {
                a[i].level=e;
                e++;
                this.max++;
            }
            if(str.charAt(i)==')')
            {
                e--;
                a[i].level=e;
            }
        }
        return a;
    }

    //处理只有+,-,*,/,sin,cos.tan的表达式
    private double getans(String s)
    {
        while (s.indexOf("s") != -1) {
            int a = s.indexOf("s");
            int b = 0, flag = 0;
            for (int i = a + 1; i < s.length(); i++) {
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                    b = i - 1;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                b = s.length() - 1;
            }
            String s1 = "";
            for (int i = a + 1; i <= b; i++) {
                s1 = s1 + s.charAt(i);
            }
            double w = Math.sin(Double.parseDouble(s1));
            if (w < 0) {
                s = s.substring(0, a) + "0" + w + s.substring(b + 1);
            }
            if (w >= 0) {
                s = s.substring(0, a) + w + s.substring(b + 1);
            }
        }

        while (s.indexOf("c") != -1) {
            int a = s.indexOf("c");
            int b = 0, flag = 0;
            for (int i = a + 1; i < s.length(); i++) {
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                    b = i - 1;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                b = s.length() - 1;
            }
            String s1 = "";
            for (int i = a + 1; i <= b; i++) {
                s1 = s1 + s.charAt(i);
            }
            double w = Math.cos(Double.parseDouble(s1));
            if (w < 0) {
                s = s.substring(0, a) + "0" + w + s.substring(b + 1);
            }
            if (w >= 0) {
                s = s.substring(0, a) + w + s.substring(b + 1);
            }
        }

        while (s.indexOf("t") != -1) {
            int a = s.indexOf("t");
            int b = 0, flag = 0;
            for (int i = a + 1; i < s.length(); i++) {
                if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                    b = i - 1;
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                b = s.length() - 1;
            }
            String s1 = "";
            for (int i = a + 1; i <= b; i++) {
                s1 = s1 + s.charAt(i);
            }
            double w = Math.tan(Double.parseDouble(s1));
            if (w < 0) {
                s = s.substring(0, a) + "0" + w + s.substring(b + 1);
            }
            if (w >= 0) {
                s = s.substring(0, a) + w + s.substring(b + 1);
            }
        }

        //处理*和/
        int ww = s.length();
        for (int i = 0; i < ww; i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                String s1 = "", s2 = "";
                if (s.charAt(i) == '*') {
                    int b1 = i - 1, flag = 0;
                    for (int j = i - 1; j >= 0; j--) //从*往前找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-') {
                            b1 = j + 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b1 = 0;
                    }
                    for (int j = b1; j <= i - 1; j++) {
                        s1 = s1 + s.charAt(j);  //s1为*之前的数据
                    }

                    int b2 = 0;
                    flag = 0;
                    for (int j = i + 1; j < s.length(); j++) //从*往后找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                            b2 = j - 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b2 = s.length() - 1;
                    }
                    for (int j = i + 1; j <= b2; j++) {
                        s2 = s2 + s.charAt(j);  //s1为*之后的数据
                    }
                    double w = Double.parseDouble(s1) * Double.parseDouble(s2);
                    s = s.substring(0, b1) + w + s.substring(b2 + 1);
                } else if (s.charAt(i) == '/') {
                    int b1 = i - 1, flag = 0;
                    for (int j = i - 1; j >= 0; j--) //从/往前找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-') {
                            b1 = j + 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b1 = 0;
                    }
                    for (int j = b1; j <= i - 1; j++) {
                        s1 = s1 + s.charAt(j);  //s1为/之前的数据
                    }

                    int b2 = 0;
                    flag = 0;
                    for (int j = i + 1; j < s.length(); j++) //从/往后找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                            b2 = j - 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b2 = s.length() - 1;
                    }
                    for (int j = i + 1; j <= b2; j++) {
                        s2 = s2 + s.charAt(j);  //s2为/之后的数据
                    }
                    if (Double.parseDouble(s2) == 0) {
                        throw new NullPointerException("除数为空");
                    }
                    double w = Double.parseDouble(s1) / Double.parseDouble(s2);
                    s = s.substring(0, b1) + w + s.substring(b2 + 1);
                }
                i = 0;  //从前往后找
                ww = s.length();
            }
        }

        //处理+和-
        ww = s.length();
        for (int i = 0; i < ww; i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                String s1 = "", s2 = "";
                if (s.charAt(i) == '+') {
                    int b1 = i - 1, flag = 0;
                    for (int j = i - 1; j >= 0; j--) //从+往前找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                            b1 = j + 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b1 = 0;
                    }
                    for (int j = b1; j <= i - 1; j++) {
                        s1 = s1 + s.charAt(j);  //s1为+之前的数据
                    }

                    int b2 = 0;
                    flag = 0;
                    for (int j = i + 1; j < s.length(); j++) //从+往后找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                            b2 = j - 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b2 = s.length() - 1;
                    }
                    for (int j = i + 1; j <= b2; j++) {
                        s2 = s2 + s.charAt(j);  //s2为+之后的数据
                    }
                    double w = Double.parseDouble(s1) + Double.parseDouble(s2);
                    s = s.substring(0, b1) + w + s.substring(b2 + 1);
                } else if (s.charAt(i) == '-') {
                    int b1 = i - 1, flag = 0;
                    for (int j = i - 1; j >= 0; j--) //从-往前找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                            b1 = j + 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b1 = 0;
                    }
                    for (int j = b1; j <= i - 1; j++) {
                        s1 = s1 + s.charAt(j);  //s1为-之前的数据
                    }

                    int b2 = 0;
                    flag = 0;
                    for (int j = i + 1; j < s.length(); j++) //从-往后找
                    {
                        if (s.charAt(j) == '+' || s.charAt(j) == '-' || s.charAt(j) == '*' || s.charAt(j) == '/') {
                            b2 = j - 1;
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        b2 = s.length() - 1;
                    }
                    for (int j = i + 1; j <= b2; j++) {
                        s2 = s2 + s.charAt(j);  //s2为-之后的数据
                    }

                    double w = Double.parseDouble(s1) - Double.parseDouble(s2);
                    s = s.substring(0, b1) + w + s.substring(b2 + 1);
                }
                i = 0;  //从前往后找
                ww = s.length();
            }
        }
        return Double.parseDouble(s);
    }

    public double output()
    {
        return getans(this.str);
    }
}

package com.my_util;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;


/**
 * 古典加密Playfair
 * 密钥是一个 5×5 的矩阵。加密和解密都是根据这个矩阵进行。
 * 加密时每次把两个明文字母变换成两个密文字母；
 * 解密时每次是把两个密文字母变换成两个明文字母。
 */
@Component
public class PlayFair {
    //秘钥
    String key="sitshooter";

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        System.out.print("请输入你的明文：");
//        String input = scan.next();
//        //处理明文
//        String plaintext = retSetPlaintext(input);
//        //处理秘钥顺便打印
//        char[][] matrix = ret_SetKey();
//        System.out.println("加密后的密文为:"+retPassword(matrix,plaintext));
//
//        //解密
//        System.out.println("解密后的密文为："+decrypt(retPassword(matrix,plaintext),matrix));
//
//    }
    public String begin_Play(){
        //获取UUID
        String uuid = UUID.randomUUID().toString();
        //获取uuid拆分
        String[] uuids=uuid.split("-");
        //将key和uuid第一位加起来
        String keyPlus = key+uuids[0];
        //将key进行处理成密匙矩阵
        char[][] key=ret_SetKey(keyPlus);
        //随机生成32位字符串
        String randomString = getRandomString(32);
        //对字符串进行处理
        String plaintext =retSetPlaintext(randomString);
        String token = retPassword(key,plaintext);
        return token;
    }
    //随机生成x位字符串
    public static String getRandomString(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
    /**
     * 处理明文
     * @param plaintext
     * @return
     */
    public static String retSetPlaintext(String plaintext){
        /**
         * 1、去除明文中重复的字母（局限是明文只限于大写字母）
         * 2、按两个字母两个字母分，如果重复，加入X
         * 3、如果长度不是偶数，结尾加X
         */
        plaintext=plaintext.toUpperCase();// 将明文转换成大写
        plaintext=plaintext.replaceAll("[^A-Z]", "");//去除所有非字母的字符
        StringBuilder sb=new StringBuilder(plaintext);
        for(int i=1;i<sb.length();i=i+2){
            //一对明文字母如果是重复的则在这对明文字母之间插入一个填充字符
            if(sb.charAt(i)==sb.charAt(i-1)){
                sb.insert(i,'X');
            }
        }
        //如果经过处理后的明文长度非偶数，则在后面加上字母x
        if(sb.length()%2!=0){
            sb.append('X');
        }
        String p=sb.toString();
        //System.out.println("处理后的明文："+p);
        return p;
    }

    /**
     * 秘钥处理
     */
    public static char[][] ret_SetKey(String keyPlus){
        /**
         * 1、将秘钥中重复的字母去除
         * 2、生成一个5*5的矩阵
         * 3、将处理后的秘钥加入
         * 4、剩下的方格按顺序加入剩余的字母
         */
        keyPlus=keyPlus.toUpperCase();// 将秘钥转换成大写
        keyPlus=keyPlus.replaceAll("[^A-Z]", "");//去除所有非字母的字符
        keyPlus=keyPlus.replaceAll("J","I");//将J转换成I
        String c_key = new String();//存储秘钥单个字符
        char[][] all_key=new char[5][5];//秘钥矩阵
        int y = 0;//记录位置，我知道很傻逼
        int z = 0;
        //去重
        for(int x=0;x<keyPlus.length();x++){
            if(c_key.indexOf(String.valueOf(keyPlus.charAt(x))) == -1){
                c_key+=keyPlus.charAt(x);
                all_key[z][y]=keyPlus.charAt(x);
                if(y==4){
                    z+=1;
                    y=0;
                    continue;
                }
                y++;
            }
        }
        //System.out.println("秘钥|处理后的秘钥："+keyPlus+"|"+c_key);
        //添加按字母表顺序排序的剩余的字母
        for(char ch='A';ch<='Z';ch++){
            if(ch!='J'&&(c_key.indexOf(String.valueOf(ch))== -1)){
                all_key[z][y]=ch;
                if(y==4){
                    z+=1;
                    y=0;
                    continue;
                }
                y++;
            }
        }
        //打印下矩阵
        //showMatrix(all_key);
        return all_key;
    }

    /**
     * 打印矩阵
     * @param matrix 矩阵
     */
    public static void showMatrix(char[][] matrix){
        System.out.println("加密矩阵为:");
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 加密
     */
    public static String retPassword(char[][] matrix,String plaintext){
        char[] ch=plaintext.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<ch.length;i=i+2){
            sb.append(encrypt(matrix,ch[i],ch[i+1]));
        }
        return sb.toString();
    }
    /**
     * 根据playfair算法对明文对进行加密
     * @param matrix 矩阵
     * @param ch1 字母1
     * @param ch2 字母2
     * @return 密文对
     */
    public static String encrypt(char[][] matrix,char ch1,char ch2){
        //获取明文对在矩阵中的位置
        int r1=0,c1=0,r2=0,c2=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(ch1==matrix[i][j]){
                    r1=i;
                    c1=j;
                }
                if(ch2==matrix[i][j]){
                    r2=i;
                    c2=j;
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        //进行规制匹配，得到密文对
        if(r1==r2){
            //明文字母对的两个字母在同一行，则截取右边的字母
            sb.append(matrix[r1][(c1+1)%5]);
            sb.append(matrix[r1][(c2+1)%5]);
        }else if(c1==c2){
            //明文字母对的两个字母在同一列，则截取下方的字母
            sb.append(matrix[(r1+1)%5][c1]);
            sb.append(matrix[(r2+1)%5][c1]);
        }else{
            //明文字母所形成的矩形对角线上的两个字母，任意选择两种方向
            //明文对中的每一个字母将由与其同行，且与另一个字母同列的字母代替
            sb.append(matrix[r1][c2]);
            sb.append(matrix[r2][c1]);
            //sb.append(matrix[r2][c1]);
            //sb.append(matrix[r1][c2]);
        }
        return sb.toString();
    }

//    /**
//     * 根据playfair算法对密文对进行解密
//     * @param matrix
//     * @param ch1 字母1
//     * @param ch2 字母2
//     * @return 明文对
//     */
//    public static String decrypt(char[][] matrix,char ch1,char ch2){
//        //获取密文对在矩阵中的位置
//        int r1=0,c1=0,r2=0,c2=0;
//        for(int i=0;i<matrix.length;i++){
//            for(int j=0;j<matrix[i].length;j++){
//                if(ch1==matrix[i][j]){
//                    r1=i;
//                    c1=j;
//                }
//                if(ch2==matrix[i][j]){
//                    r2=i;
//                    c2=j;
//                }
//            }
//        }
//        StringBuilder sb=new StringBuilder();
//        //进行规制匹配，得到明文对
//        if(r1==r2){
//            //密文字母对的两个字母在同一行，则截取左边的字母
//            sb.append(matrix[r1][(c1-1+5)%5]);
//            sb.append(matrix[r1][(c2-1+5)%5]);
//        }else if(c1==c2){
//            //密文字母对的两个字母在同一列，则截取上方的字母
//            sb.append(matrix[(r1-1+5)%5][c1]);
//            sb.append(matrix[(r2-1+5)%5][c1]);
//        }else{
//            //密文字母所形成的矩形对角线上的两个字母，任意选择两种方向
//            sb.append(matrix[r1][c2]);
//            sb.append(matrix[r2][c1]);
//            //sb.append(matrix[r2][c1]);
//            //sb.append(matrix[r1][c2]);
//        }
//        sb.append(' ');
//        return sb.toString();
//    }

//    /**
//     * 对密文进行解密
//     * @param C 密文
//     * @param matrix 矩阵
//     * @return 明文
//     */
//    public static String decrypt(String C,char[][] matrix){
//        C=C.toUpperCase();
//        C=C.replaceAll("[^A-Z]", "");//去除所有非字母的字符
//        char[] ch=C.toCharArray();
//        StringBuilder sb=new StringBuilder();
//        for(int i=0;i<ch.length;i=i+2){
//            sb.append(decrypt(matrix,ch[i],ch[i+1]));
//        }
//        return sb.toString();
//    }
}

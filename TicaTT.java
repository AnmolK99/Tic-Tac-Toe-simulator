import java.util.*;
class TicaTT
{
    Scanner sc=new Scanner(System.in);
    int i,turn;
    int ar[][]=new int[3][3];
    int li[]=new int[9];
    public void clearli()
    {
        int ci=0;
        for(;ci<9;ci++)
        li[ci]=0;
    }
        
    public void displaymatrix()
    {
        System.out.flush();
        System.out.println();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(ar[i][j]==1)
                System.out.print("|X| ");
                if(ar[i][j]==2)
                System.out.print("|O| ");
                if(ar[i][j]==0)
                System.out.print("| | ");
            }
            System.out.println();
        }
    }
    
    public double heuris(int sign,int x,int y)
    {
        int ar1[]=new int[8];
        int ar2[]=new int[8];
        int sign1;
        for(i=0;i<8;i++)
        {
            ar1[i]=0;
            ar2[i]=0;
        }
        if(sign==1)
        sign1=2;
        else
        sign1=1;
        double sum=0.0;
        int chanlin=-1;
        int linepr[]=new int[8];
        if(true)
        {
            for(i=0;i<3;i++)
            {
                if(ar[i][0]==sign) // Column 1
                ar1[0]++;
                if(ar[i][0]==sign1)
                ar2[0]++;
                if(ar[2][i]==sign) // Row 3
                ar1[1]++;
                if(ar[2][i]==sign1)
                ar2[1]++;
                if(ar[i][2]==sign) // Column 3
                ar1[2]++;
                if(ar[i][2]==sign1)
                ar2[2]++;
                if(ar[0][i]==sign) // Row 1
                ar1[3]++;
                if(ar[0][i]==sign1)
                ar2[3]++;
                if(ar[i][i]==sign) // Diagonal 1
                ar1[4]++;
                if(ar[i][i]==sign1)
                ar2[4]++;
                if(ar[i][2-i]==sign) // Diagonal 2  right-left
                ar1[5]++;
                if(ar[i][2-i]==sign1)
                ar2[5]++;
                if(ar[i][1]==sign) // Column 2
                ar1[6]++;
                if(ar[i][1]==sign1)
                ar2[6]++;
                if(ar[1][i]==sign) // Row 2
                ar1[7]++;
                if(ar[1][i]==sign1)
                ar2[7]++;
                
                
                if(i==x && 0==y)
                linepr[0]=1;
                if(2==x && i==y)
                linepr[1]=1;
                if(i==x && 2==y)
                linepr[2]=1;
                if(0==x && i==y)
                linepr[3]=1;
                if(i==x && i==y)
                linepr[4]=1;
                if(i==x && (2-i)==y)
                linepr[5]=1;
                if(i==x && 1==y)
                linepr[6]=1;
                if(1==x && i==y)
                linepr[7]=1;
            }
            
            int kk[]=new int[8];
            for(int ii=0;ii<8;ii++)
            kk[ii]=0;
            for(int j=0;j<8;j++)
            {
                /** System.out.print(" ["+((double)ar1[i]-(3/4)*ar2[i])+"]"); */
                if(ar1[j]==3)
                {
                    System.out.println("\n");
                    displaymatrix();
                    System.out.println("Computer WON");
                    System.exit(0);
                }
                if(ar2[j]==3)
                {
                    System.out.println("\n");
                    displaymatrix();
                    System.out.println("YOU WON");
                    System.exit(0);
                }
                // System.out.println(ar1[j] +" "+ar2[j]);
                if(ar1[j]==1 && ar2[j]==2 && linepr[j]==1)
                {
                    kk[j]+=-100;
                    // System.out.println("it happened");
                }
                if(ar2[j]>0 && ar1[j]==0)
                kk[j]+=ar2[j];
                if(ar2[j]==0 && ar1[j]>0)
                kk[j]-=ar1[j];
                if(ar1[j]==2 && ar2[j]==0)
                kk[j]-=ar1[j]*2;
            }
            for(int ii=0;ii<8;ii++)
            sum=sum+kk[ii];
        }
        return sum;
    }
    
    public void Mark()
    {
        double arr[]=new double[9];
        for(int i=0;i<9;i++)
        arr[i]=0;
        /** System.out.println(" [1] [2] [3] [4] [5] [6] [7] [8]"); */
        for(int j=0;j<3;j++)
        {
            for(int k=0;k<3;k++)
            {
                if(ar[j][k]==0)
                {
                    ar[j][k]=2;
                    // System.out.print("\n");
                    arr[3*j+k]=heuris(2,j,k);
                    ar[j][k]=0;
                }
                else
                arr[3*j+k]=100;
            }
        }
        
        int v=0,j,p=0,alt=0;
        double k=arr[0];
        
        
        for(j=0;j<9;j++)
        {
            // if(arr[j]!=10)
            // alt=j;
            // System.out.println(arr[j]);
            if(arr[j]<=k )
            {
                k=arr[j];
                v=j;
                //li[p++]=j;
            }
            
        }
        if(v==0 && k==0)
        {
            v=alt;
        }
        ar[v/3][v%3]=2;
    }
    
    public void cal() throws Exception
    {
        int arra[]=new int[9];
        double u=Math.random();
        // System.out.println(u);
        turn=(int)(u*10);
        int k=1;
        while(true)
        {
            int plumvar=1;
            for(int i=0;i<9;i++)
            {
                if(ar[i/3][i%3]==0)
                {plumvar=0; break;}
            }
            if(plumvar==1)
            {
                System.out.println("Match Draw");
                System.exit(0);
            }
            if(turn%2==0)
            {
                displaymatrix();
                int px=sc.nextInt();
                int py=sc.nextInt();
                // System.out.println("\033[H\033[2J");
                // System.out.flush();
                if(px<0 || px>3 || py<0 || py>3)
                {
                    System.out.println("Wrong Inputs please Enter Again");
                    continue;
                }
                if(ar[px-1][py-1]!=0)
                {
                    System.out.println("Place Filled");
                    continue;
                }
                ar[px-1][py-1]=1;
                Mark();
            }
            else
            {
                displaymatrix();
                int px=0,py=0;
                if(k==0)
                {
                    px=sc.nextInt();
                    py=sc.nextInt();
                    if(px<0 || px>3 || py<0 || py>3)
                    {
                    System.out.println("Wrong Inputs please Enter Again");
                    continue;
                        }
                    if(ar[px-1][py-1]!=0)
                    {
                        System.out.println("Place Filled");
                        continue;
                    }
                    k=1;
                }
                else
                {
                    Mark();
                    displaymatrix();
                    px=sc.nextInt();
                    py=sc.nextInt();
                    if(px<0 || px>3 || py<0 || py>3)
                    {
                    System.out.println("Wrong Inputs please Enter Again");
                    continue;
                        }
                    if(ar[px-1][py-1]!=0)
                    {
                    System.out.println("Place Filled");
                    continue;
                    }
                }
                // System.out.println("\033[H\033[2J");
                System.out.flush();
                if(ar[px-1][py-1]!=0)
                {
                    System.out.println("Place Filled");
                    k=0;
                    continue;
                }
                ar[px-1][py-1]=1;
            }
        }
    }
    public static void main(String args[]) throws Exception
    {
        System.out.println("Welcome to Tic-Tac-Toe Game");
        TicaTT obj=new TicaTT();
        obj.cal();
    }
}   
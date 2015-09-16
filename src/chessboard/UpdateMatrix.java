package chessboard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author THE_KATOCH!
 */
public class UpdateMatrix {
    public static int[][] updatemat(int arr[][],int dimension,int player)
    {
        int narr[][]=new int[dimension][dimension];
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                narr[i][j]=arr[i][j];
            }    
        }  
        if(player==0)
        {
            int update = -1;
            for(int i=dimension-1;i>=0;i--)
            {
                for(int j=dimension-1;j>=0;j--)
                {
                    if(narr[i][j]==1 && narr[i-1][j]==0)
                    {    narr[i][j]=0;
                         narr[i-1][j]=1;
                         update=1;
                         break;
                    }
                }
                if(update==1)
                    break;
            }    
        }    
        else if(player==1)
        {
        
            int update = -1;
            for(int i=0;i<dimension;i++)
            {
                for(int j=0;j<dimension;j++)
                {
                    if(narr[i][j]==2&& narr[i+1][j]==0)
                    {    narr[i][j]=0;
                         narr[i+1][j]=2;
                         update=1;
                         break;
                    }
                }
                if(update==1)
                    break;
            }    
        }    
        return narr;
    }        
}

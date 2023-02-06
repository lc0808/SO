package com.so;

import com.so.controladores.ProcessoGeralController;


public class Executavel
{
    public static void main( String[] args ) throws Exception
    {
        ProcessoGeralController processController = new ProcessoGeralController();
        processController.exec();
    }
}

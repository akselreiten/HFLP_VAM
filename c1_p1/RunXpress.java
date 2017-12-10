RunXpress.java 
import com.dashoptimization.*; 
 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import java.io.PrintWriter; 
import java.io.UnsupportedEncodingException; 
import java.lang.*; 
 
public class RunXpress { 
 
    public static void main(String[] args) throws XPRMCompileException, IOException { 
        for (double weightp1 = 0; weightp1 <=1.1; weightp1 = weightp1 + 0.5) { 
            printXpressFile(weightp1); 
            runXpress(); 
        } 
    } 
 
    private static void printXpressFile(double weightp1) throws FileNotFoundException, UnsupportedEncodingException { 
        String filename = "weights.txt"; 
        PrintWriter writer = new PrintWriter(filename, "UTF-8"); 
 
        //weightViolation 
        writer.print("Weights : ["); 
        writer.print(weightp1); 
        writer.print(" "); 
        writer.print(1 - weightp1); 
        writer.println("]"); 
    } 
 
    private static void runXpress() throws XPRMCompileException, IOException { 
        XPRM mosel; 
        XPRMModel mod; 
 
        //Initialize model 
        mosel = new XPRM(); 
 
        //Compile model 
        mosel.compile("Filnavn.mos"); 
 
        //Load bim file 
        mod = mosel.loadModel("case.bim"); 
 
        //Execute model 
        System.out.println("Run 'Filnavn.mos'"); 
        mod.run(); 
 
        //Model done 
        System.out.println("Xpress complete"); 
        mod.run(); 
 
        //Stop if no solution is found 
        if(mod.getProblemStatus()!=mod.PB_OPTIMAL) 
            System.exit(1); 
 
    } 
 
} 
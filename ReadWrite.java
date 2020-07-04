package bankSystem;

import java.io.*;

public class ReadWrite {

    private String serializationFile;

    public ReadWrite(){
        super();
        serializationFile = "phase2/data_storage.ser";
    }

    /**
     * stores object in serializationFile
     * @param object the object to be stored in serializationFile
     */
    public void serialize (Object object) {
        try (
                FileOutputStream file = new FileOutputStream(serializationFile);
                BufferedOutputStream buffer = new BufferedOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(buffer)
        ) {
            out.writeObject(object);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("IOException caught");
        }

    }

    /**
     * returns object stored in serializationFile
     * @return returns object stored in serializationFile
     */
    public Object deserialize() {
        Object obj = null;
        try (
                FileInputStream file = new FileInputStream(serializationFile);
                BufferedInputStream buffer = new BufferedInputStream(file);
                ObjectInputStream in = new ObjectInputStream(buffer)
        ) {
            obj = in.readObject();
        } catch (IOException e) {
            System.out.println("IOException caught");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException caught");
        }
        return obj;
    }

    /**
     * Writes the given string to the given filename, returns whether writing was successful
     * @param filename file that the information is to be logged in
     * @param toBeLogged String that is to be logged
     * @return whether writing was successful
     */
    boolean writeToFile(String filename, String toBeLogged) {
        try{
            FileWriter file = new FileWriter(filename, true);
            BufferedWriter buffer = new BufferedWriter(file);
            buffer.write(toBeLogged);
            buffer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
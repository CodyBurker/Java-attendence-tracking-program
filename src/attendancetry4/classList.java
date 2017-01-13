/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package attendancetry4;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author cody
 */
public class classList {
    private String firstName;
    private String lastName;
    private String timeStamp;
    private String prof;
    private String cls;
    
    public classList() {
    }
    public classList(String firstName, String lastName, String prof, String cls){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.prof = prof;
        this.cls = cls;
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String first){
        this.firstName = first;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String last){
        this.firstName = last;
    }
    public String getProf(){
        return this.prof;
    }
    public void setProf(String prof){
        this.firstName = prof;
    }
    public String getCls(){
        return this.cls;
    }
    public void setClass(String cls){
        this.cls = cls;
    }
}

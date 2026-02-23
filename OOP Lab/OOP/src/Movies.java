public class Movies {

    String diname,FeturedSong;
    
    public void Titanic(String a, String b) {
        
        this.diname = a;
        this.FeturedSong = b;

    }
    public void print(){
        System.out.println("Sum is:"+diname);
         System.out.println("Sum is:"+FeturedSong);
    }
    public static void main(String [] args){
        Movies c = new Movies();
        c.Titanic("James Cameron","My life is going on");
        c.print();
    }
}


public class Chrono{
    int min;
    int sec;
    int secSurFPS;

    public Chrono(){
        this.min = 5;
        this.sec = 1;
        this.secSurFPS = 0;
    }

    int UpdateChrono(int FPS){
        this.secSurFPS--;
        if (this.secSurFPS <= 0){
            this.secSurFPS = FPS;
            this.sec--;
            if (this.sec < 0){
                this.sec = 59;
                this.min--;
                if (this.min < 0){
                    this.min = 0;
                    this.sec = 0;
                    return 1;
                }
            }
        }
        return 0;
    }
}

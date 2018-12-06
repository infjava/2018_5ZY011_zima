
/**
 * Write a description of class Tetromino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tetromino {
    
    private boolean[][] tvar;
    private int poziciaX;
    private int poziciaY;
    
    /**
     * Constructor for objects of class Tetromino
     */
    public Tetromino(boolean[][] tvar) {
        this.tvar = tvar;
        this.poziciaY = 0;
        
        int sirkaTetromina = this.tvar[0].length;
        this.poziciaX = Displej.SIRKA / 2 - 1 - sirkaTetromina / 2;
        
        this.zobraz();
    }
    
    public void posunVpravo() {
        if (this.poziciaX + this.tvar[0].length == Displej.SIRKA) {
            return;
        }
        
        this.skry();
        this.poziciaX++;
        this.zobraz();
    }
    
    public void posunVlavo() {
        if (this.poziciaX <= 0) {
            return;
        }
        
        this.skry();
        this.poziciaX--;
        this.zobraz();
    }
    
    public void posunDole() {
        if (this.poziciaY + this.tvar.length >= Displej.VYSKA) {
            return;
        }   
        
        this.skry();
        this.poziciaY++;
        this.zobraz();
    }  
    
    public void otoc() {
        int novaSirka = this.tvar.length;
        int novaVyska = this.tvar[0].length;
        
        if (this.poziciaX + novaSirka > Displej.SIRKA || this.poziciaY + novaVyska > Displej.VYSKA) {
            return;
        }
        
        this.skry();
        
        boolean[][] novyTvar = new boolean[novaVyska][novaSirka];
        
        for (int stareY = 0; stareY < this.tvar.length; stareY++) {
            for (int stareX = 0; stareX < this.tvar[0].length; stareX++) {
                int noveX = novaSirka - 1 - stareY;
                int noveY = stareX;
                
                novyTvar[noveY][noveX] = this.tvar[stareY][stareX];
            }
        }
        
        this.tvar = novyTvar;
        
        this.zobraz();
    }
    
    private void zobraz() {
        Displej displej = Displej.getDisplej();
        
        for (int y = 0; y < this.tvar.length; y++) {
            for (int x = 0; x < this.tvar[y].length; x++) {
                if (this.tvar[y][x]) {
                    displej.zasviet(this.poziciaX + x, this.poziciaY + y);
                }
            }
        }
    }
    
    private void skry() {
        Displej displej = Displej.getDisplej();
        
        for (int y = 0; y < this.tvar.length; y++) {
            for (int x = 0; x < this.tvar[y].length; x++) {
                if (this.tvar[y][x]) {
                    displej.zhasni(this.poziciaX + x, this.poziciaY + y);
                }
            }
        }
    }
    
    public boolean jeNaDne() {
        return this.poziciaY + this.tvar.length >= Displej.VYSKA;
    }
}

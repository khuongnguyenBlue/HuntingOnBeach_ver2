/**
 * Created by Laptop88 on 7/13/2016.
 */
public class Player extends GameObject {
    public int type;
    public int exp;
    public int money;
    public int checkGun;
    public int damage;
    public void shot(){
        if (type==0){
            damage=20;
        }
        else if (type==1) {
            damage = 40;
        }
        else damage = 60;
    }
    void changeGun(){
        type = checkGun%3;
    }
    void useItem(int item){
        if (item==1){
            //nem luu dan, tat ca mat nua mau
        }
        else{
            if (item==2){
                //thuy loi
            }
            else {
                //support plane xuat hien
            }
        }
    }

}

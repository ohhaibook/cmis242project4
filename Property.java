public class Property implements StateChangeable<State>
{
  String address = "";
  int numBedrooms;
  int squareFootage;
  int price;
  State status;

  public Property(String ad, int nb, int sf, int p) {
    address = ad;
    numBedrooms = nb;
    squareFootage = sf;
    price = p;
    status = State.FOR_SALE;
  }

  public void changeState(State s) {
    status = s;
  }

  @Override
  public String toString() {
    String s = ("ADDRESS: " + address + "\nBEDROOMS: " + numBedrooms + "\nSQUARE FEET: " + squareFootage + "\nPRICE: $" + price + "\nSTATUS: " + status);
    return s;
  }
}

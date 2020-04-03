import java.math.RoundingMode

class Main {

    // contains information about [Product, Group, Cost]
    def static products = [
            ["A", "G1", 20.1],
            ["B", "G2", 98.4],
            ["C", "G1", 49.7],
            ["D", "G3", 35.8],
            ["E", "G3", 105.5],
            ["F", "G1", 55.2],
            ["G", "G1", 12.7],
            ["H", "G3", 88.6],
            ["I", "G1", 5.2],
            ["J", "G2", 72.4]]

// contains information about Category classification based on product Cost
// [Category, Cost range from (inclusive), Cost range to (exclusive)]
// i.e. if a Product has Cost between 0 and 25, it belongs to category C1
// ranges are mutually exclusive and the last range has a null as upper limit.
    def category = [
            ["C3", 50, 75],
            ["C4", 75, 100],
            ["C2", 25, 50],
            ["C5", 100, null],
            ["C1", 0, 25]]

// contains information about margins for each product Category
// [Category, Margin (either percentage or absolute value)]
    def margins = [
            "C1" : "20%",
            "C2" : "30%",
            "C3" : "0.4",
            "C4" : "50%",
            "C5" : "0.6"]


    // YOUR CODE GOES BELOW THIS LINE
    // EDIT OR ADD COODE AS NEEDED, MAKE SURE YOU PRINT TO SCREEN THE AVERAGE FOR THE INPUT GROUP

    static void main(String[] args) {
        for (inputline in System.in.readLines()) {
            println(getGroupAverage(inputline));
        }
    }

    public static getGroupAverage(String group) {
        Main main = new Main();
        def suma = 0;
        def elementos = 0;
        def subgrupos = main.products.findAll{ it -> it[1].equals(group)}
        subgrupos.each{ item ->
            main.category.findAll{ it -> item[2]>it[1] && item[2]<it[2] || item[2]>=it[1] && it[2] == null}.each{
                Double valor = main.margins.get(it[0]).endsWith("%")?Double.valueOf(main.margins.get(it[0]).split("%")[0])/100:Double.valueOf(main.margins.get(it[0]))
                suma += item[2]*(1+valor)
            }
        }
        return(Math.round(suma/subgrupos.size()*10)/10)
    }
}
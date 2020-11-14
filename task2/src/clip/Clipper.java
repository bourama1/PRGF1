package clip;

import model.Line;
import model.Polygon;

import java.util.List;

public class Clipper {

    public static Polygon clip(Polygon inPoly, Polygon clipPoly){
        Polygon outPoly = null;
        if (clipPoly.points.size()<=2)
            return inPoly;
       /* TODO List<Line> clipLines ...
        for (int i= 0; i<; i++){
            Edge edge = new Edge(i,clipPoly);
            outPoly.clear();
            Point v1 = in.last;
            for (Point v2 : in){
                if (v2 inside edge){
                    if (v1 not inside edge)
                    out.add(intersection(v1,v2,edge)); //var.4
                    out.add(v2); //var.1,4
                }else{
                    if (v1 inside edge)
                    out.add(intersection(v1,v2,edge)); //var.2
                }
                v1 = v2;
            } 
            //aktualizuj ořezávaný polygon
        }
*/
        //TODO
        return outPoly;
    }


    //volani pres instanci tridy
    // Polygon inPoly;
    //Polygon clipPoly;
    //Clipper clipper = new Clipper();
    //Polygon outPolygon = clipper.clip(polygon, clipPolygon);


    //volani staticke metody tridy
    //Polygon inPoly;
    //Polygon clipPoly;
    //Polygon outPolygon = Clipper.clip(polygon, clipPolygon);
}

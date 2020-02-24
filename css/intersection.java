public void getintersection(LatLng getYourLocationLatLng, LatLng getTheirLocationLatLng){

        // circle 1: (x0, y0), radius r0
        double x0 = getYourLocationLatLng.latitude; //x
        double y0 = getYourLocationLatLng.longitude; //y
        int r0 = 10;

        // circle 2: (x1, y1), radius r1
        double x1 = getTheirLocationLatLng.latitude; //x
        double y1 = getTheirLocationLatLng.longitude; //y
        int r1 = 10;

        //distance between midpoints
        double d = Math.pow((Math.pow((x1-x0),2) + Math.pow((y1-y0),2)), 0.5);
        if(d > r0 + r1){
            drawcircle(getYourLocationLatLng);
        }
        else if(d < Math.abs(r0-r1)){
            drawcircle(getYourLocationLatLng);
        }
        else if(d == 0){
            drawcircle(getYourLocationLatLng);
        }
        else{
            // a = r0^2 - r1^2 + distance^2 / 2d
            double a = Math.pow(r0,2) - Math.pow(r1,2) + Math.pow(d,2) / (2*d);

            // h = (r0^2 - a^2)^ 0.5
            double h = Math.pow(Math.pow(r0,2) - Math.pow(a,2),0.5);

            // midpoint (x2,y2) = x0 + a(x1-x0) / d
            double x2 = (x0 + (a * (x1-x0))) / d;
            double y2 = (y0 + (a * (y1-y0))) / d;


            double x3 = (x2 + (h * (y1-y0))) / d;
            double y3 = (y2 - (h * (x1- x0))) / d;

            double x4 = x2 - (h * (y1-y0)) / d;
            double y4 = y2 + (h * (x1-x0)) / d;

            mMap.addPolygon(new PolygonOptions()
                    .add(new LatLng(x1, y1), new LatLng(x2, y2), new LatLng(x3,y3), new LatLng(x4,y4))
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE));

        }
    }
    public void drawcircle(LatLng getYourLocationLatLng){
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(getYourLocationLatLng.latitude, getYourLocationLatLng.longitude))
                .radius(1000)
                .strokeWidth(10)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(128, 255, 0, 0)));
    }
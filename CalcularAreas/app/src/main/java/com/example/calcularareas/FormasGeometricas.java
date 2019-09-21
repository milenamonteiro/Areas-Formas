package com.example.calcularareas;

class FormasGeometricas {

    double areaQuadrado(double lado){
        return Math.pow(lado, 2);
    }
    double areaTriangulo(double base, double altura){
        return (base*altura)/2;
    }
    double areaRetangulo(double maior, double menor){
        return maior*menor;
    }
    double areaCirculo(double raio){
        return Math.pow(raio, 2)*Math.PI;
    }
}

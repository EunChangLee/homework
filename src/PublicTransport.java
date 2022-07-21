abstract class PublicTransport {
    int _fuelVolume = 100; //주유량
    int _speed = 0; //현재 속도
    int _maxSonNim; //최대 승객 수
    int _nowSonNim = 0; //현재 승객 수
    //운행 시작
    abstract void startService();
    //운행 종료
    abstract void endService();

    //속도 변경
    abstract public void speedChange(int speed);

    //연료 소모
    abstract public void usedFuel(int fuel);

    //승객 탑승
    public void getOnThe(int sonNim){
        if(_nowSonNim + sonNim > _maxSonNim){
            System.out.println("최대 승객 수를 초과하였습니다.");
            System.out.println("현재 승객 수 : " + _nowSonNim);
        }
        System.out.println("현재 승객 수 : " + _nowSonNim);
        _nowSonNim += sonNim;
        System.out.println("승차 후 승객 수 : " + _nowSonNim);
    }
}
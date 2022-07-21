class Taxi extends PublicTransport {
    String _dest = ""; //목적지
    int _dist = 0; //목적지까지의 거리
    int _basicDist = 1; //기본 거리
    int _totalFare = 0;
    int _nowFare = 0;
    int _fare = 3000; //기본 요금
    int _distFare = 1000; //거리당 요금
    public State _state;
    public enum State{
        빈차, 운행, 운행불가
    }
    static int _number = 0;
    int _myNumber = 0;
    int getNumber(){
        return _myNumber;
    }
    void setNumber(int number){
        _myNumber = number;
    }

    Taxi(){
        _fuelVolume = 100;
        _maxSonNim = 4;
        _number += 1;
        setNumber(_number);
        startService();
    }
    @Override
    void startService() {
        if(_fuelVolume >= 10){
            System.out.println(_myNumber + "번 택시 운행을 시작합니다.");
            _state = State.빈차;
        }
        System.out.println("연료가 부족합니다. 운행할 수 없습니다.");
        _state = State.운행불가;
    }

    @Override
    void endService() {

    }
    //손님 태우기
    public void getOnThe(int sonNim, String dest, int dist){
        if(_state != State.빈차){
            System.out.println("빈차가 아닙니다.");
            return;
        }
        if(sonNim > 4){
            System.out.println("4명까지만 타실 수 있습니다.");
            return;
        }

        super._nowSonNim = sonNim;
        _dest = dest;
        _dist = dist;
        distAddCost();
        _state = State.운행;
        System.out.println("탑승 승객 수 : " + _nowSonNim);
        System.out.println("잔여 승객 수 : " + (super._maxSonNim - _nowSonNim));
        System.out.println("기본 요금 : " + _fare);
        System.out.println("목적지 : " + _dest);
        System.out.println("목적지까지 거리 : " + _dist + "km");
        System.out.println("지불할 요금 : " + _nowFare);
        System.out.println("상태 : " + _state);
    }
    //거리당 요금 추가
    public void distAddCost(){
        _totalFare += _fare + (_distFare*(_dist-_basicDist));
        _nowFare = _fare + (_distFare*(_dist-_basicDist));
    }
    //요금 결제
    public void payment(){
        System.out.println("요금은 " + _nowFare + "입니다.");
        _nowFare = 0;
    }
    //도착. 손님 내리고 상태를 빈차로 만들기
    public void arrive(){
        _nowSonNim = 0;
        _state = State.빈차;
    }
    //속도 변경
    @Override
    public void speedChange(int speed) {
        if(_state != State.운행){
            System.out.println("운행중이 아닙니다.");
            return;
        }
        checkFuelVolume();

        _speed += speed;

        if(_speed < 0)
            _speed = 0;

        if(_speed > 150)
            _speed = 150;

        System.out.println("현재 속력 : " + _speed + "km 입니다.");
    }

    @Override
    public void usedFuel(int fuel) {
        if(_fuelVolume < fuel){
            System.out.println("연료가 부족합니다.");
            return;
        }

        _fuelVolume -= fuel;
        System.out.println("연료가 " + fuel + " 소모되었습니다.");
        checkFuelVolume();
        System.out.println("누적 요금 : " + _totalFare);
    }
    public void checkFuelVolume(){
        System.out.println("현재 연료량 " + _fuelVolume);

        if(_fuelVolume < 10){
            System.out.println("주유가 필요합니다.");
            _state = State.운행불가;
            System.out.println("상태 : " + _state);
        }
    }

}
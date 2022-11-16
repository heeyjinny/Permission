package com.heeyjinny.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.heeyjinny.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //3
    //권한요청런처 만들기
    //3-1
    //권한을 처리하는 프로퍼티 선언 : onCreate 바깥 전역변수로 만듦
    //권한처리 컨트랙트(RequestPermission())는 파라미터로 하나의 문자열 사용하므로 런처의 제네릭은 String 정의
    lateinit var activityResult: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //4
        //사용자에게 승인 요청
        //카메라 버튼을 클릭하면 승인요청의 런처를 실행하는 코드 작성
        //4-1
        //런처의 속성 launch()의 파라미터에는
        //앞에서 작성한 설정파일(AndroidManifest.xml)에 명세했던 카메라 권한 1개만 넘겨주면 됨
        binding.btnCamera.setOnClickListener {
            activityResult.launch(android.Manifest.permission.CAMERA)
        }


        //3-2
        //registerForActivityResult()를 런처로 만들어 미리선언한 전역변수 activityResult에 저장
        activityResult = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->

            //3-3
            //결과값의 승인 결과(it -> isGranted(별칭으로 변경))의 타입은 Boolean 이므로 결과는 true, false로 반환
            //카메라 권한이 승인(true)라면 startProcess() 호출하여 카메라 실행 (startProcess()메서드는 따로 만들것임)
            //미승인(false)라면 finish() 호출하여 앱 종료
            if (isGranted){
                startProcess()
            }else{
                finish()
            }

        }

        //FIN.
        //카메라 버튼을 클릭하면 권한요청 팝업창이 생성되고 승인 시 토스트 메시지 출력

        //1
        //위험권한 처리하기 : 카메라 권한 사용 시
        //설정파일(AndroidManifest.xml)을 수정한 다음 소스코드에도 추가로 처리함

        //1-1
        //buid.gradle에서 뷰바인딩 설정하고 사용할 registerForActivityResult의 의존성 확인
        //설정파일(AndroidManifest.xml) 명세하기
        //app - manifests 디렉터리 밑 - AndroidManifest.xml - 카메라 권한 추가

        //2
        //위험권한 처리의 소스코드 처리
        //결과값 처리를 위해 registerForActivityResult 사용
        //registerForActivityResult : 액티비티 이외에도 컨트랙트 종류에 따라 안드로이드의 기본기능 사용가능(카메라, 갤러리)
        //또한 권한을 다루는 컨트랙트 사용 시 권한 요청 팝업을 띄우고 승인 여부에 대한 결과 처리 가능
        //기본 사용 방법 : registerForActivityResult() 메서드 파라미터에
        //ActivityResultContracts.컨트랙트 종류를 사용하여 컨트랙트에 맞는 타입의 ActivityResultLauncher 런처 생성
        //런처 실행 시 메서드에 작성한 코드 블록이 실행되는 구조

    }//onCreate

    //3-4
    //카메라 실행을 승인 했을 때 실행할
    //startProcess()메서드 생성
    fun startProcess(){
        //3-5
        //승인이면 프로그램을 진행한다는 메시지를 띄움
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_SHORT).show()
    }

}//MainActivity
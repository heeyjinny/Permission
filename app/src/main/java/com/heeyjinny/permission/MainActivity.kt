package com.heeyjinny.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heeyjinny.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //1
        //위험권한 처리하기
        //설정파일(AndroidManifest.xml)을 수정한 다음 소스코드에도 추가로 처리함

        //1-1
        //설정파일(AndroidManifest.xml) 명세하기
        //bulid.gradle(Module) - 뷰바인딩 설정 - registerForActivityResult 관련 의존성 추가(모르겠음 ㅜㅜ)

        //1-2
        //app - manifests 디렉터리 밑 - AndroidManifest.xml - 사용할 권한 추가
        //카메라 권한 추가...


    }
}
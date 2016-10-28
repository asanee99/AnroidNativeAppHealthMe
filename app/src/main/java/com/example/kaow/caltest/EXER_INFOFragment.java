package com.example.kaow.caltest;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class EXER_INFOFragment extends Activity {
    TextView exerHead;
    ListView exerInfo;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exer__info);

        exerHead = (TextView) findViewById(R.id.exer_header);
        exerInfo = (ListView) findViewById(R.id.exer_info);

        Bundle bundle = getIntent().getExtras();

        pos = bundle.getInt("pos");


        String[] walking = new String[] {
                "1. ต้องเดินก้าวยาวๆ เพื่อให้กล้ามเนื้อมัดใหญ่หลายมัดได้ทำงานหนัก เช่น กล้ามเนื้อ สะโพก ขาส่วนบน และกล้ามเนื้อน่อง",
                "2. เดินต่อเนื่องกันไปอย่างน้อย 20 นาที",
                "3. เดินเป็นประจำสม่ำเสมอ อย่างน้อยสัปดาห์ละ 3 - 5 วัน",
                "4. การก้าวขาให้ใช้แรงจากขาท่อนบนและสะโพกเป็นหลัก",
                "5. อย่ากระแทกส้นเท้าหรือเดินด้วยส้น",
                "6. เดินให้สบายไม่เกร็งกล้ามเนื้อ"
        };

        String[] run = new String[] {
                "1. ควรอบอุ่นร่างกายด้วยการยืดกล้ามเนื้อ บริหารข้อต่อก่อนทุกครั้ง เพื่อเป็นการกระตุ้นให้ร่างกายพร้อมจะทำงานหนัก",
                "2. ออกวิ่งด้วยความเร็วช้าๆ ก่อนเมื่อร่างกายปรับตัวได้แล้วจึงเพิ่มความเร็วขึ้นตามต้องการ",
                "3. การวิ่งแต่ละครั้ง ควรจะวิ่งอย่างน้อย 20 นาที",
                "4. ควรวิ่งสัปดาห์ละ 3 - 5 วัน วันละ 1 ครั้ง",
                "5. การวิ่งเพื่อสุขภาพควรจะลงส้นเท้าก่อน แล้วจึงถ่ายน้ำหนักไปที่ผ่าเท้า",
                "6. ก่อนหยุดการวิ่งควรจะค่อยๆ ลดความเร็วลงจนกลายเป็นการเดินเพื่อให้ร่างกายปรับตัว"
        };

        String[] jump = new String[] {
                "1. ใส่รองเท้าที่มีความยืดหยุ่นและรับแรงกระแทกได้ดี เพื่อป้องกันการบาดเจ็บจากเชือกตี และป้องกันการบาดเจ็บข้อเท้า",
                "2. กระโดดเชือกอย่างน้อยสัปดาห์ละ 3 - 5 วัน"
        };

        String[] aerobics = new String[] {
                "1. ควรอบอุ่นร่างกายเพื่อเป็นการยืดกล้ามเนื้อและบริหารข้อต่อต่าง ๆ ควรใช้เวลาประมาณ 5 - 10 นาที",
                "2. ช่วงของการออกกำลังกาย เพื่อกระตุ้นให้ระบบหายใจและระบบไหลเวียนเลือดได้ทำงานหนักขึ้น ควรใช้เวลา 15 - 20 นาที",
                "3. ควรมีระยะผ่อนคลาย (cool down) เป็นการปรับสภาพการทำงานของระบบต่าง ๆ ในร่างกายให้คืนสู่สภาวะปกติ โดยค่อย ๆ ลดความเร็ว ความแรงของท่าเต้นลง ควรใช้เวลาประมาณ 5 - 10 นาที"

        };

        String[] taichi = new String[] {
                "1. ทำใจให้สงบ ควบคุมสมาธิให้มั่นคง",
                "2. เคลื่อนไหวร่างกายอย่างช้า ๆ สม่ำเสมอ ไม่เกร็งกล้ามเนื้อมากเกินไป",
                "3. ระยะเวลาในการฝึกแต่ละครั้ง ควรใช้เวลาอย่างน้อย 25 นาที",
                "4. ฝึกหายใจให้สมอดคล้องกับท่าทางการเคลื่อนไหว เช่น หายใจเข้าเมื่อยกแขนหรือยึดแขน",
                "5. ควรจะฝึกหรือรำมวยจีนอย่างน้อย 5 วัน ต่อสัปดาห์"
        };

        String[] biking = new String[] {
                "1. ความสูงของเบาะนั่งต้องเหมาะสมคือเมื่อนั่งบนเบาะ เท้าที่วางบนบันไดที่ต่ำเข่าจะงอเล็กน้อยโดยทำมุมประมาณ 5 องศา หากตั้งเบาะต่ำไปอาจจะทำให้ปวดเข่าเมื่อขี่จักรยาน",
                "2. ต้องตรวจข้อล็อกต่างๆว่าอยู่ในตำแหน่งที่ถูกต้องและแน่นหนา",
                "3. วิธีทดสอบอีกวิธีหนึ่งคือให้วางส้นเท้าบนบันไดขั้นต่ำสุด เข่าจะเหยียดตรงพอดีี",
                "4. ความสูงของมือจับปรับให้พอดี โดยปรับให้สูงแล้วค่อยเลื่อนต่ำลงมา ตำแหน่งที่เหมาะสมคือข้อศอกงอเล็กน้อย ระยะห่างพอดี และจับสบายไม่ปวดหลัง การปรับนี้ผู้ขี่ต้องปรับให้พอดีกับตัวเอง",
                "5. การเลือกรองเท้า ไม่ควรใช้รองเท้าสำหรับวิ่งหรือรองเท้าสำหรับการเต้นแอโรบิก เพราะพื้นรองเท้านุ่มเกินไป พื้นรองเท้าสำหรับขี่จักยานควรจะแข็งพอสมควร เพื่อจะได้ขี่จักยานอย่างมีประสิทธิภาพ",

        };

        if(pos >= 5) {
            exerHead.setText("หลักการปั่นจักรยานเบื้องต้น");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < biking.length; ++i) {
                list.add(biking[i]);
                ArrayAdapter adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                exerInfo.setAdapter(adapter);

            }
        } else if (pos >= 4) {
            exerHead.setText("หลักการรำมวยจีน");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < taichi.length; ++i) {
                list.add(taichi[i]);
                ArrayAdapter adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                exerInfo.setAdapter(adapter);

            }
        } else if (pos >= 3) {
            exerHead.setText("หลักการเอโรบิกดานซ์เบื้องต้น");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < aerobics.length; ++i) {
                list.add(aerobics[i]);
                ArrayAdapter adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                exerInfo.setAdapter(adapter);

            }
        } else if (pos >= 2) {
            exerHead.setText("หลักการกระโดดเชือกเบื้องต้น");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < jump.length; ++i) {
                list.add(jump[i]);
                ArrayAdapter adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                exerInfo.setAdapter(adapter);

            }
        } else if (pos >= 1) {
            exerHead.setText("หลักการวิ่งเบื้องต้น");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < run.length; ++i) {
                list.add(run[i]);
                ArrayAdapter adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                exerInfo.setAdapter(adapter);

            }
        }else {
            exerHead.setText("หลักการเดินเบื้องต้น");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < walking.length; ++i) {
                list.add(walking[i]);
                ArrayAdapter adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, list);
                exerInfo.setAdapter(adapter);

            }
        }

    }

}


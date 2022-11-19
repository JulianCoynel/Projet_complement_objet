package Capteurs;

import java.util.ArrayList;
import Segments_de_route.*;
import Semaphores.*;

public abstract class Capteur {
	private Segment_route son_segment;
	private ArrayList<Semaphore> ses_semaphores;
}

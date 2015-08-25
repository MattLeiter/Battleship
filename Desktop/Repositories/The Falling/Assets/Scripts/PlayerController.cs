using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {

	public float playerForce = 75.0f;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	void FixedUpdate () 
	{
		bool playerActive = Input.GetButton("Fire1");
		
		if (playerActive)
		{
			rigidbody2D.AddForce(new Vector2(0, playerForce));
		}
	}
}

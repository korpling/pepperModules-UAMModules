/**
 * Copyright 2009 Humboldt University of Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package de.hu_berlin.german.korpling.saltnpepper.model.uam;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getFeatures <em>Features</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getSourceText <em>Source Text</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getStart <em>Start</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getEnd <em>End</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getId <em>Id</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getLayer <em>Layer</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment()
 * @model
 * @generated
 */
public interface Segment extends EObject {
	/**
	 * Returns the value of the '<em><b>Features</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' attribute.
	 * @see #setFeatures(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_Features()
	 * @model
	 * @generated
	 */
	String getFeatures();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getFeatures <em>Features</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Features</em>' attribute.
	 * @see #getFeatures()
	 * @generated
	 */
	void setFeatures(String value);

	/**
	 * Returns the value of the '<em><b>Source Text</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Text</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Text</em>' reference.
	 * @see #setSourceText(Text)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_SourceText()
	 * @model
	 * @generated
	 */
	Text getSourceText();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getSourceText <em>Source Text</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Text</em>' reference.
	 * @see #getSourceText()
	 * @generated
	 */
	void setSourceText(Text value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Integer)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_Start()
	 * @model
	 * @generated
	 */
	Integer getStart();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Integer value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(Integer)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_End()
	 * @model
	 * @generated
	 */
	Integer getEnd();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Integer value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' container reference.
	 * @see #setLayer(Layer)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_Layer()
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getSegments
	 * @model opposite="segments" required="true" transient="false"
	 * @generated
	 */
	Layer getLayer();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getLayer <em>Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layer</em>' container reference.
	 * @see #getLayer()
	 * @generated
	 */
	void setLayer(Layer value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see #setState(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getSegment_State()
	 * @model
	 * @generated
	 */
	String getState();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see #getState()
	 * @generated
	 */
	void setState(String value);

} // Segment

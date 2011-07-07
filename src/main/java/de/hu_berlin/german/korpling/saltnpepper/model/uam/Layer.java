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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getSegments <em>Segments</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getLang <em>Lang</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getComplete <em>Complete</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getUamDocument <em>Uam Document</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getLayer()
 * @model
 * @generated
 */
public interface Layer extends EObject {
	/**
	 * Returns the value of the '<em><b>Segments</b></em>' containment reference list.
	 * The list contents are of type {@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment}.
	 * It is bidirectional and its opposite is '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segments</em>' containment reference list.
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getLayer_Segments()
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment#getLayer
	 * @model opposite="Layer" containment="true"
	 * @generated
	 */
	EList<Segment> getSegments();

	/**
	 * Returns the value of the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lang</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lang</em>' attribute.
	 * @see #setLang(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getLayer_Lang()
	 * @model
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getLang <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang</em>' attribute.
	 * @see #getLang()
	 * @generated
	 */
	void setLang(String value);

	/**
	 * Returns the value of the '<em><b>Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Complete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Complete</em>' attribute.
	 * @see #setComplete(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getLayer_Complete()
	 * @model
	 * @generated
	 */
	String getComplete();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getComplete <em>Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Complete</em>' attribute.
	 * @see #getComplete()
	 * @generated
	 */
	void setComplete(String value);

	/**
	 * Returns the value of the '<em><b>Uam Document</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getLayers <em>Layers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uam Document</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uam Document</em>' container reference.
	 * @see #setUamDocument(UAMDocument)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getLayer_UamDocument()
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument#getLayers
	 * @model opposite="layers" required="true" transient="false"
	 * @generated
	 */
	UAMDocument getUamDocument();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getUamDocument <em>Uam Document</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uam Document</em>' container reference.
	 * @see #getUamDocument()
	 * @generated
	 */
	void setUamDocument(UAMDocument value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage#getLayer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Layer
